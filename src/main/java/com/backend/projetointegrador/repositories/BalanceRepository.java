package com.backend.projetointegrador.repositories;

import com.backend.projetointegrador.domain.entities.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Optional<Balance> findByAccountId(Long accountId);

    Optional<Balance> findByAccountUserEmail(String email);
}
