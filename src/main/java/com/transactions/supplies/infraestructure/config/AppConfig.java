package com.transactions.supplies.infraestructure.config;

import com.transactions.supplies.domain.ports.api.SuppliesIn;
import com.transactions.supplies.domain.ports.spi.SuppliesOut;
import com.transactions.supplies.domain.service.SuppliesService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    @Bean
    public SuppliesIn suppliesService(SuppliesOut userOut) {
        return new SuppliesService(userOut);
    }
}