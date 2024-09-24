package com.transactions.supplies.infraestructure.controller;

import com.transactions.supplies.application.dto.request.SuppliesRequestDTO;
import com.transactions.supplies.application.dto.response.SuppliesResponseDTO;
import com.transactions.supplies.application.handler.SuppliesHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supplies")
@RequiredArgsConstructor
public class SuppliesController {

    private final SuppliesHandler suppliesHandler;

    @Operation(summary = "Crea o actualiza un suministro", description = "Endpoint para crear o actualizar un suministro. Si el suministro ya existe, se actualizará con los nuevos datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Suministro creado o actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<SuppliesResponseDTO> createOrUpdateSupply(
            @Parameter(description = "Datos del suministro a crear o actualizar", required = true)
            @Valid @RequestBody SuppliesRequestDTO suppliesRequestDTO) {
        SuppliesResponseDTO createdSupply = suppliesHandler.createOrUpdateSupplies(suppliesRequestDTO);
        return new ResponseEntity<>(createdSupply, HttpStatus.CREATED);
    }
}
