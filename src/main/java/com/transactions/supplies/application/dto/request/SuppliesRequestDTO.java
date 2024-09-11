package com.transactions.supplies.application.dto.request;
import com.transactions.supplies.application.utils.Constants;
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
    @NotBlank(message = Constants.SUPPLY_NAME_NOT_BLANK)
    private String name;
    @Positive(message = Constants.SUPPLY_QUANTITY_POSITIVE)
    private int quantity;
}
