package com.transactions.supplies.application.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuppliesRequestDTO {
    @NotBlank(message = "El suministro no puede ser vacío")
    private String name;
    @Positive(message = "La cantidad debe ser mayor a 0")
    private int quantity;
}
