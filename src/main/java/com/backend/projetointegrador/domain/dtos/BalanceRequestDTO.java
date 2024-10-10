package com.backend.projetointegrador.domain.dtos;

public record BalanceRequestDTO(
        String operation,
        Float value
) {
}
