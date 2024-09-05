package com.transactions.supplies.application.mapper;

import com.transactions.supplies.application.dto.request.SuppliesRequestDTO;
import com.transactions.supplies.application.dto.response.SuppliesResponseDTO;
import com.transactions.supplies.domain.model.Supplies;
import org.springframework.stereotype.Component;
@Component
public class SuppliesMapper {
    public Supplies toDomain(SuppliesRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Supplies supplies = new Supplies();
        supplies.setName(dto.getName());
        supplies.setQuantity(dto.getQuantity());
        return supplies;
    }

    public SuppliesResponseDTO toDto(Supplies supplies) {
        if (supplies == null) {
            return null;
        }
        SuppliesResponseDTO dto = new SuppliesResponseDTO();
        dto.setId(supplies.getId());
        dto.setName(supplies.getName());
        dto.setQuantity(supplies.getQuantity());
        return dto;
    }
}