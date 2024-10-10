package com.backend.projetointegrador.domain.dtos;

public record InvestmentBuyRequestDTO(
        Float buyPrice,
        Long productId
) {
}
