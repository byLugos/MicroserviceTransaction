package com.transactions.supplies.domain.ports.api;
import com.transactions.supplies.domain.model.Supplies;
import java.util.List;
public interface SuppliesIn {
    Supplies createSupply(String name, int quantity);
    List<Supplies> getAllSupplies();
}
