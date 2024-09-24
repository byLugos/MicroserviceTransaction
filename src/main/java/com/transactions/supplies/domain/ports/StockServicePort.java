package com.transactions.supplies.domain.ports;

public interface StockServicePort {
    int getStock(Long articleId);
    void updateStock(Long articleId, int quantityChange);
}
