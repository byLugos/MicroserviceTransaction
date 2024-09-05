package com.transactions.supplies.domain.service;

import com.transactions.supplies.domain.exceptions.InvalidSupplyName;
import com.transactions.supplies.domain.model.Supplies;
import com.transactions.supplies.domain.ports.spi.SuppliesOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class SuppliesServiceTest {

    @Mock
    private SuppliesOut suppliesOut;

    @InjectMocks
    private SuppliesService suppliesService;

    @BeforeEach
    void setUp() {
        suppliesService = new SuppliesService(suppliesOut);
    }

    @Test
    void testCreateSupply_NewSupply() {
        // Arrange
        String name = "Paper";
        int quantity = 10;
        Supplies savedSupply = new Supplies(1L, name, quantity);

        when(suppliesOut.existsByName(name)).thenReturn(Optional.empty());
        when(suppliesOut.save(any(Supplies.class))).thenReturn(savedSupply);

        // Act
        Supplies result = suppliesService.createSupply(name, quantity);

        // Assert
        assertNotNull(result);
        assertEquals(savedSupply.getId(), result.getId());
        assertEquals(savedSupply.getName(), result.getName());
        assertEquals(savedSupply.getQuantity(), result.getQuantity());
        verify(suppliesOut, times(1)).existsByName(name);
        verify(suppliesOut, times(1)).save(any(Supplies.class));
    }

    @Test
    void testCreateSupply_ExistingSupply() {
        String name = "Paper";
        int existingQuantity = 5;
        int additionalQuantity = 10;
        Supplies existingSupply = new Supplies(1L, name, existingQuantity);
        Supplies updatedSupply = new Supplies(1L, name, existingQuantity + additionalQuantity);

        when(suppliesOut.existsByName(name)).thenReturn(Optional.of(existingSupply));
        when(suppliesOut.save(any(Supplies.class))).thenReturn(updatedSupply);

        Supplies result = suppliesService.createSupply(name, additionalQuantity);

        assertNotNull(result);
        assertEquals(updatedSupply.getId(), result.getId());
        assertEquals(updatedSupply.getName(), result.getName());
        assertEquals(existingQuantity + additionalQuantity, result.getQuantity());
        verify(suppliesOut, times(1)).existsByName(name);
        verify(suppliesOut, times(1)).save(any(Supplies.class));
    }

    @Test
    void testCreateSupply_InvalidName() {
        String invalidName = "   ";
        int quantity = 10;

        InvalidSupplyName exception = assertThrows(InvalidSupplyName.class, () -> {
            suppliesService.createSupply(invalidName, quantity);
        });

        assertEquals("El suministro no puede ser vacío.", exception.getMessage());
        verify(suppliesOut, never()).existsByName(any());
        verify(suppliesOut, never()).save(any());
    }

    @Test
    void testGetAllSupplies() {
        List<Supplies> mockSupplies = List.of(
                new Supplies(1L, "Paper", 10),
                new Supplies(2L, "Pencil", 20)
        );
        when(suppliesOut.findAll()).thenReturn(mockSupplies);
        List<Supplies> result = suppliesService.getAllSupplies();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Paper", result.get(0).getName());
        assertEquals("Pencil", result.get(1).getName());
        verify(suppliesOut, times(1)).findAll();
    }
}