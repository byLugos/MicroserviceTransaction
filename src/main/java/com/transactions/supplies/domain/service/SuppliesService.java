package com.transactions.supplies.domain.service;
import com.transactions.supplies.domain.exceptions.InvalidSupplyName;
import com.transactions.supplies.domain.model.Supplies;
import com.transactions.supplies.domain.ports.api.SuppliesIn;
import com.transactions.supplies.domain.ports.spi.SuppliesOut;
import java.util.List;
import java.util.Optional;
public class SuppliesService implements SuppliesIn {
    private final SuppliesOut suppliesOut;
    public SuppliesService(SuppliesOut suppliesOut) {
        this.suppliesOut = suppliesOut;
    }
    @Override
    public Supplies createSupply(String name, int quantity) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidSupplyName("El suministro no puede ser vacío.");
        }
        Optional<Supplies> existingSupplyOpt = suppliesOut.existsByName(name);
        Supplies supply;
        if (existingSupplyOpt.isPresent()) {
            supply = existingSupplyOpt.get();
            supply.setQuantity(supply.getQuantity() + quantity);
        } else {
            supply = new Supplies(null, name, quantity);
        }
        return suppliesOut.save(supply);
    }
    @Override
    public List<Supplies> getAllSupplies() {
        return suppliesOut.findAll();
    }
}
