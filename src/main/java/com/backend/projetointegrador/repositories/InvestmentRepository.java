package com.backend.projetointegrador.repositories;

import com.backend.projetointegrador.domain.entities.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    List<Investment> findByAccountId(Long accountId);
}
