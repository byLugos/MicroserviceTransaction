package com.transactions.supplies.infraestructure.controller;
import com.transactions.supplies.application.dto.request.SuppliesRequestDTO;
import com.transactions.supplies.application.dto.response.SuppliesResponseDTO;
import com.transactions.supplies.application.handler.SuppliesHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SuppliesControllerTest {

    @Mock
    private SuppliesHandler suppliesHandler;

    @InjectMocks
    private SuppliesController suppliesController;

    @BeforeEach
    void setUp() {
        suppliesHandler = mock(SuppliesHandler.class);
        suppliesController = new SuppliesController(suppliesHandler);
    }

    @Test
    void testCreateSupplies_ValidInput() {
        // Arrange
        SuppliesRequestDTO requestDTO = new SuppliesRequestDTO("Paper", 10);
        SuppliesResponseDTO responseDTO = new SuppliesResponseDTO(1L, "Paper", 10);

        when(suppliesHandler.createOrUpdateSupplies(any(SuppliesRequestDTO.class))).thenReturn(responseDTO);

        // Act
        ResponseEntity<SuppliesResponseDTO> responseEntity = suppliesController.createSupplies(requestDTO);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Paper", responseEntity.getBody().getName());
        assertEquals(10, responseEntity.getBody().getQuantity());

        verify(suppliesHandler, times(1)).createOrUpdateSupplies(any(SuppliesRequestDTO.class));
    }



}