package com.transactions.supplies.application.handler;
import com.transactions.supplies.application.dto.request.SuppliesRequestDTO;
import com.transactions.supplies.application.dto.response.SuppliesResponseDTO;
import com.transactions.supplies.application.mapper.SuppliesMapper;
import com.transactions.supplies.domain.model.Supplies;
import com.transactions.supplies.domain.ports.api.SuppliesIn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SuppliesHandlerTest {

    @Mock
    private SuppliesMapper suppliesMapper;

    @Mock
    private SuppliesIn suppliesIn;

    @InjectMocks
    private SuppliesHandler suppliesHandler;

    @BeforeEach
    void setUp() {
        suppliesHandler = new SuppliesHandler(suppliesMapper, suppliesIn);
    }

    @Test
    void testCreateOrUpdateSupplies_ValidInput() {
        SuppliesRequestDTO requestDTO = new SuppliesRequestDTO("Paper", 10);
        Supplies supplies = new Supplies(1L, "Paper", 10);
        Supplies createdSupplies = new Supplies(1L, "Paper", 10);
        SuppliesResponseDTO responseDTO = new SuppliesResponseDTO(1L, "Paper", 10);

        when(suppliesMapper.toDomain(requestDTO)).thenReturn(supplies);
        when(suppliesIn.createSupply(("Paper"),(10))).thenReturn(createdSupplies);
        when(suppliesMapper.toDto(createdSupplies)).thenReturn(responseDTO);

        SuppliesResponseDTO result = suppliesHandler.createOrUpdateSupplies(requestDTO);

        assertNotNull(result);
        assertEquals("Paper", result.getName());
        assertEquals(10, result.getQuantity());
        verify(suppliesMapper, times(1)).toDomain(requestDTO);
        verify(suppliesIn, times(1)).createSupply("Paper", 10);
        verify(suppliesMapper, times(1)).toDto(createdSupplies);
    }

    @Test
    void testCreateOrUpdateSupplies_InvalidName() {
        // Arrange
        SuppliesRequestDTO requestDTO = new SuppliesRequestDTO("", 10);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            suppliesHandler.createOrUpdateSupplies(requestDTO);
        });
        assertEquals("El nombre del suministro no puede estar vacío.", exception.getMessage());
        verify(suppliesMapper, never()).toDomain(any());
        verify(suppliesIn, never()).createSupply(any(), anyInt());
    }

    @Test
    void testCreateOrUpdateSupplies_InvalidQuantity() {
        // Arrange
        SuppliesRequestDTO requestDTO = new SuppliesRequestDTO("Paper", 0);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            suppliesHandler.createOrUpdateSupplies(requestDTO);
        });
        assertEquals("La cantidad debe ser mayor a 0.", exception.getMessage());
        verify(suppliesMapper, never()).toDomain(any());
        verify(suppliesIn, never()).createSupply(any(), anyInt());
    }

    @Test
    void testCreateOrUpdateSupplies_NullInput() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            suppliesHandler.createOrUpdateSupplies(null);
        });
        assertEquals("El nombre del suministro no puede estar vacío.", exception.getMessage());
        verify(suppliesMapper, never()).toDomain(any());
        verify(suppliesIn, never()).createSupply(any(), anyInt());
    }
}