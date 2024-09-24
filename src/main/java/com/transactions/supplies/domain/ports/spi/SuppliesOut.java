package com.transactions.supplies.domain.ports.spi;
import com.transactions.supplies.domain.model.Supplies;
import java.util.List;
import java.util.Optional;
public interface SuppliesOut {
    Supplies save(Supplies supplies);
    Optional<Supplies> existsByName(String name);
    List<Supplies> findAll();
}
