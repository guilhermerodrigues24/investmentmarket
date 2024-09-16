package com.backend.projetointegrador.domain.dtos;

import java.time.Instant;

public record ProductResponseDTO(
        Long id,
        String name,
        Instant dueDate,
        Float dailyYield,
        ProductTypeResponseDTO productType
) {
}
