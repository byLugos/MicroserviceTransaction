package com.transactions.supplies.infraestructure.jpaout.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "supplies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuppliesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private LocalDate creationDate;
    @Column(nullable = false)
    private LocalDate restockingDate;
}

