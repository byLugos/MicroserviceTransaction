package com.transactions.supplies.infraestructure.config;

import com.transactions.supplies.domain.ports.ArticleServicePort;
import com.transactions.supplies.domain.ports.StockServicePort;
import com.transactions.supplies.domain.ports.api.SuppliesIn;
import com.transactions.supplies.domain.ports.spi.SuppliesOut;
import com.transactions.supplies.domain.service.SuppliesService;
import com.transactions.supplies.infraestructure.feing.ArticleServiceAdapter;
import com.transactions.supplies.infraestructure.feing.StockFeignClient;
import com.transactions.supplies.infraestructure.feing.StockServiceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    @Bean
    public SuppliesIn suppliesService(SuppliesOut userOut, ArticleServicePort articleServicePort, StockServicePort stockServicePort) {
        return new SuppliesService(userOut,articleServicePort,stockServicePort);
    }
    @Bean
    public ArticleServicePort articleServicePort(StockFeignClient stockFeignClient) {
        return new ArticleServiceAdapter(stockFeignClient);
    }
    @Bean
    public StockServicePort stockServicePort(StockFeignClient stockFeignClient) {
        return new StockServiceAdapter(stockFeignClient);
    }
}