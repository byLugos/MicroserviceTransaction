package com.transactions.supplies.domain.model;

import java.time.LocalDate;

public class Supplies {
    private Long id;
    private String name;
    private LocalDate creationDate;
    private LocalDate restockingDate;

    private int quantity;
    public Supplies() {
    }

    public Supplies(Long id, String name, LocalDate creationDate, LocalDate restockingDate, int quantity) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.restockingDate = restockingDate;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getRestockingDate() {
        return restockingDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
