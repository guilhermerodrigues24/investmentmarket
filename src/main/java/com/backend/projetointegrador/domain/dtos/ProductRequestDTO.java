package com.backend.projetointegrador.domain.dtos;

import java.time.Instant;

public record ProductRequestDTO(
        String name,
        Float minimumValue,
        Instant dueDate,
        Long productTypeId
) {
}
