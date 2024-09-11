package com.transactions.supplies.infraestructure.controller;

import com.transactions.supplies.application.dto.request.SuppliesRequestDTO;
import com.transactions.supplies.application.dto.response.SuppliesResponseDTO;
import com.transactions.supplies.application.handler.SuppliesHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/supplies")
@RequiredArgsConstructor
public class SuppliesController {
    private final SuppliesHandler suppliesHandler;
    @PostMapping
    public ResponseEntity<SuppliesResponseDTO> createSupplies(@Valid @RequestBody SuppliesRequestDTO suppliesRequestDTO) {
        SuppliesResponseDTO createdSupply = suppliesHandler.createOrUpdateSupplies(suppliesRequestDTO);
        return new ResponseEntity<>(createdSupply, HttpStatus.CREATED);
    }
}
