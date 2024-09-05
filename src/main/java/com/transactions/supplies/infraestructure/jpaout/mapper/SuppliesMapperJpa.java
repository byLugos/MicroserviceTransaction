package com.transactions.supplies.infraestructure.jpaout.mapper;

import com.transactions.supplies.domain.model.Supplies;
import com.transactions.supplies.infraestructure.jpaout.entity.SuppliesEntity;
import org.springframework.stereotype.Component;

@Component
public class SuppliesMapperJpa {
    public SuppliesEntity toEntity(Supplies supplies) {
        if (supplies == null) {
            return null;
        }
        SuppliesEntity entity = new SuppliesEntity();
        entity.setId(supplies.getId());
        entity.setName(supplies.getName());
        entity.setQuantity(supplies.getQuantity());
        return entity;
    }
    public Supplies toDomain(SuppliesEntity suppliesEntity) {
        if (suppliesEntity == null) {
            return null;
        }
        Supplies supplies = new Supplies();
        supplies.setId(suppliesEntity.getId());
        supplies.setName(suppliesEntity.getName());
        supplies.setQuantity(suppliesEntity.getQuantity());
        return supplies;
    }
}