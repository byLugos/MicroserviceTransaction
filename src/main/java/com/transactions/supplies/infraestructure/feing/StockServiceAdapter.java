package com.transactions.supplies.infraestructure.feing;

import com.transactions.supplies.domain.ports.StockServicePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StockServiceAdapter implements StockServicePort {
    private final StockFeignClient stockFeignClient;
    @Override
    public int getStock(Long articleId) {
        return stockFeignClient.getStock(articleId);
    }
    @Override
    public void updateStock(Long articleId, int quantityChange) {
        stockFeignClient.updateStock(articleId, quantityChange);
    }
}
