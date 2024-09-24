package com.transactions.supplies.application.handler;

import com.transactions.supplies.application.dto.request.SuppliesRequestDTO;
import com.transactions.supplies.application.dto.response.SuppliesResponseDTO;
import com.transactions.supplies.application.mapper.SuppliesMapper;
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
        Supplies createdSupplies = suppliesIn.createSupply(dto.getName(), dto.getQuantity());
        return suppliesMapper.toDto(createdSupplies);
    }
}
