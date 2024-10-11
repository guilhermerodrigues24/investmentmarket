package com.backend.projetointegrador.domain.dtos;

import java.time.Instant;

public record InvestmentResponseDTO(
        Long id,
        Float buyPrice,
        Instant buyTime,
        Float sellPrice,
        Instant sellTime,
        Boolean isSold,
        ProductResponseDTO product
) {
}
