package com.transactions.supplies.infraestructure.jpaout.mapper;

import com.transactions.supplies.domain.model.Supplies;
import com.transactions.supplies.infraestructure.jpaout.entity.SuppliesEntity;
import com.transactions.supplies.infraestructure.utils.Constants;
import org.springframework.stereotype.Component;

@Component
public class SuppliesMapperJpa {

    public SuppliesEntity toEntity(Supplies supplies) {
        if (supplies == null) {
            throw new IllegalArgumentException(Constants.SUPPLIES_NULL);
        }
        SuppliesEntity entity = new SuppliesEntity();
        entity.setId(supplies.getId());
        entity.setName(supplies.getName());
        entity.setQuantity(supplies.getQuantity());
        entity.setCreationDate(supplies.getCreationDate());
        entity.setRestockingDate(supplies.getRestockingDate());
        return entity;
    }

    public Supplies toDomain(SuppliesEntity suppliesEntity) {
        if (suppliesEntity == null) {
            return null;
        }
        return new Supplies(
                suppliesEntity.getId(),
                suppliesEntity.getName(),
                suppliesEntity.getCreationDate(),
                suppliesEntity.getRestockingDate(),
                suppliesEntity.getQuantity()
        );
    }
}
