package com.transactions.supplies.domain.model;

import java.time.LocalDate;

public class Sales {
    private Long id;
    private String name;
    private LocalDate saleDate;
    private Double total;
    public Sales(Long id, String name, LocalDate saleDate, Double total) {
        this.id = id;
        this.name = name;
        this.saleDate = saleDate;
        this.total = total;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public LocalDate getSaleDate() {
        return saleDate;
    }
    public Double getTotal() {
        return total;
    }
}
