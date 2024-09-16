package com.backend.projetointegrador.domain.dtos;

import java.time.Instant;

public record InvestmentResponseDTO(
        Long id,
        Float investedValue,
        Float sellPrice,
        Instant sellTime,
        AccountResponseDTO account,
        ProductResponseDTO product
) {
}
