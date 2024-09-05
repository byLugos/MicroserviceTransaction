package com.transactions.supplies.application.dto.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SuppliesResponseDTO {
    private Long id;
    private String name;
    private int quantity;
}