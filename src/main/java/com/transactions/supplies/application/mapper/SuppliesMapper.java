package com.transactions.supplies.application.mapper;

import com.transactions.supplies.application.dto.request.SuppliesRequestDTO;
import com.transactions.supplies.application.dto.response.SuppliesResponseDTO;
import com.transactions.supplies.domain.model.Supplies;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface SuppliesMapper {
    SuppliesResponseDTO toDto(Supplies supplies);
    Supplies toEntity(SuppliesRequestDTO suppliesRequestDTO);
}
