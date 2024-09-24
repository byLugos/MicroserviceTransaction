package com.transactions.supplies.domain.service;
import com.transactions.supplies.domain.exceptions.*;
import com.transactions.supplies.domain.model.Supplies;
import com.transactions.supplies.domain.ports.ArticleServicePort;
import com.transactions.supplies.domain.ports.StockServicePort;
import com.transactions.supplies.domain.ports.api.SuppliesIn;
import com.transactions.supplies.domain.ports.spi.SuppliesOut;
import com.transactions.supplies.domain.utils.Constants;

import java.util.List;
import java.util.Optional;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class SuppliesService implements SuppliesIn {
    private final SuppliesOut suppliesOut;
    private final ArticleServicePort articleServicePort;
    private final StockServicePort stockServicePort;

    public SuppliesService(SuppliesOut suppliesOut, ArticleServicePort articleServicePort, StockServicePort stockServicePort) {
        this.suppliesOut = suppliesOut;
        this.articleServicePort = articleServicePort;
        this.stockServicePort = stockServicePort;
    }

    @Override
    public Supplies createSupply(String name, int requestedQuantity) {
        try {
            Long articleId = articleServicePort.findArticleIdByName(name);
            if (articleId == null) {
                throw new InvalidSupplyName(Constants.ARTICLE_NOT_FOUND);
            }

            Optional<Supplies> existingSupplyOpt = suppliesOut.existsByName(name);
            Supplies supply;
            LocalDate currentDate = LocalDate.now();
            if (existingSupplyOpt.isPresent()) {
                supply = existingSupplyOpt.get();
            } else {
                LocalDate restockingDate = calculateRestockingDate(currentDate);
                supply = new Supplies(null, name, currentDate, restockingDate, 0);
            }

            int availableStock = stockServicePort.getStock(articleId);

            if (requestedQuantity < 0 && availableStock + requestedQuantity < 0) {
                    throw new InsufficientStock(Constants.INSUFFICIENT_STOCK);
                }

            stockServicePort.updateStock(articleId, requestedQuantity);
            supply.setQuantity(supply.getQuantity() + requestedQuantity);
            return suppliesOut.save(supply);

        } catch (InvalidRequestSource e) {
            throw new InvalidRequestSource(Constants.SOURCE_NOT_FOUND);
        } catch (InvalidRequest e) {
            throw new InvalidRequest(Constants.INVALID_REQUEST);
        } catch (InternalErrorException e) {
            throw new InternalErrorException(Constants.EXTERNAL_ERROR);
        } catch (Exception e) {
            throw new InternalError(Constants.INTERNAL_ERROR, e);
        }
    }


    @Override
    public List<Supplies> getAllSupplies() {
        return suppliesOut.findAll();
    }

    private LocalDate calculateRestockingDate(LocalDate creationDate) {
        LocalDate restockingDate = creationDate.plusDays(Constants.SEVENTEEN);
        DayOfWeek dayOfWeek = restockingDate.getDayOfWeek();

        if (dayOfWeek == DayOfWeek.SATURDAY) {
            restockingDate = restockingDate.plusDays(Constants.TWO);
        } else if (dayOfWeek == DayOfWeek.SUNDAY) {
            restockingDate = restockingDate.plusDays(Constants.ONE);
        }

        return restockingDate;
    }
}