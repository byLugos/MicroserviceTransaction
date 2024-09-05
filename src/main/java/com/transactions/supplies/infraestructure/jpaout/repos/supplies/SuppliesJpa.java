package com.transactions.supplies.infraestructure.jpaout.repos.supplies;

import com.transactions.supplies.infraestructure.jpaout.entity.SuppliesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SuppliesJpa extends JpaRepository<SuppliesEntity, Long> {
    Optional<SuppliesEntity> findByName(String name);
}