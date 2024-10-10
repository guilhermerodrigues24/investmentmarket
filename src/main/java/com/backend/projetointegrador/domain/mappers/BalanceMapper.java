package com.backend.projetointegrador.domain.mappers;

import com.backend.projetointegrador.domain.dtos.BalanceResponseDTO;
import com.backend.projetointegrador.domain.entities.Balance;

public class BalanceMapper {
    public static BalanceResponseDTO toResponseDTO(Balance balance) {
        return new BalanceResponseDTO(balance.getAmount());
    }
}
