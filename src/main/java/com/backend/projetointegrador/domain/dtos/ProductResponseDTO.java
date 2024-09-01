package com.backend.projetointegrador.domain.dtos;

import java.time.Instant;

public record ProductResponseDTO(
        Long id,
        String name,
        Float minimumValue,
        Instant dueDate,
        ProductTypeResponseDTO productType
) {
}
