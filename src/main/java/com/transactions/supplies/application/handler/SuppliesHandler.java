package com.transactions.supplies.application.handler;

import com.transactions.supplies.application.dto.request.SuppliesRequestDTO;
import com.transactions.supplies.application.dto.response.SuppliesResponseDTO;
import com.transactions.supplies.application.mapper.SuppliesMapper;
import com.transactions.supplies.application.utils.Constants;
import com.transactions.supplies.domain.model.Supplies;
import com.transactions.supplies.domain.ports.api.SuppliesIn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SuppliesHandler {
    private final SuppliesMapper suppliesMapper;
    private final SuppliesIn suppliesIn;
    public SuppliesResponseDTO createOrUpdateSupplies(SuppliesRequestDTO dto) {
        if (dto == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException(Constants.SUPPLY_NAME_CANNOT_BE_EMPTY);
        }
        if (dto.getQuantity() <= 0) {
            throw new IllegalArgumentException(Constants.SUPPLY_QUANTITY_MUST_BE_GREATER_THAN_ZERO);
        }
        Supplies supplies = suppliesMapper.toDomain(dto);
        Supplies createdSupplies = suppliesIn.createSupply(supplies.getName(), supplies.getQuantity());
        return suppliesMapper.toDto(createdSupplies);
    }
}
