package com.backend.projetointegrador.repositories;

import com.backend.projetointegrador.domain.entities.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
}
