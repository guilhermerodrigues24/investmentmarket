package com.backend.projetointegrador.repositories;

import com.backend.projetointegrador.domain.entities.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {

}
