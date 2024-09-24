package com.transactions.supplies.infraestructure.feing;

import com.transactions.supplies.domain.ports.ArticleServicePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ArticleServiceAdapter implements ArticleServicePort {
    private final StockFeignClient stockFeignClient;
    @Override
    public Long findArticleIdByName(String name) {
        return stockFeignClient.findArticleIdByName(name);
    }
}
