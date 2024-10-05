package com.backend.projetointegrador.domain.dtos;

public record InvestmentRequestDTO(
        Float buyPrice,
        Long productId
) {
}
