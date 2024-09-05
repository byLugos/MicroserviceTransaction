package com.transactions.supplies.infraestructure.jpaout.repos.supplies;
import com.transactions.supplies.domain.model.Supplies;
import com.transactions.supplies.domain.ports.spi.SuppliesOut;
import com.transactions.supplies.infraestructure.jpaout.entity.SuppliesEntity;
import com.transactions.supplies.infraestructure.jpaout.mapper.SuppliesMapperJpa;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
@Component
@AllArgsConstructor
public class SuppliesJpaOut implements SuppliesOut {
    private final SuppliesJpa suppliesJpa;
    private final SuppliesMapperJpa suppliesMapperJpa;
    @Override
    @Transactional
    public Supplies save(Supplies supplies) {
        Optional<SuppliesEntity> existingEntityOpt = suppliesJpa.findByName(supplies.getName());
        SuppliesEntity savedEntity;

        if (existingEntityOpt.isPresent()) {
            SuppliesEntity existingEntity = existingEntityOpt.get();
            existingEntity.setQuantity(existingEntity.getQuantity() + supplies.getQuantity());
            savedEntity = suppliesJpa.save(existingEntity);
        } else {
            SuppliesEntity suppliesEntity = suppliesMapperJpa.toEntity(supplies);
            savedEntity = suppliesJpa.save(suppliesEntity);
        }

        return suppliesMapperJpa.toDomain(savedEntity);
    }
    @Override
    public Optional<Supplies> existsByName(String name) {
        return suppliesJpa.findByName(name)
                .map(suppliesMapperJpa::toDomain);
    }
    @Override
    public List<Supplies> findAll() {
        return suppliesJpa.findAll().stream()
                .map(suppliesMapperJpa::toDomain)
                .toList();
    }
}