package com.transactions.supplies.infraestructure.config;

import com.transactions.supplies.infraestructure.feing.exceptions.CustomErrorEncoder;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class FeignClientConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                String jwtToken = request.getHeader("Authorization");
                if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
                    requestTemplate.header("Authorization", jwtToken);
                    System.out.println("JWT Token in Feign Request: " + jwtToken);
                } else {
                    System.out.println("No JWT Token found in HttpServletRequest");
                }
            }
        };
    }
    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorEncoder();
    }
}
