package com.transactions.supplies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SuppliesApplication {
	public static void main(String[] args) {
		SpringApplication.run(SuppliesApplication.class, args);
	}

}
