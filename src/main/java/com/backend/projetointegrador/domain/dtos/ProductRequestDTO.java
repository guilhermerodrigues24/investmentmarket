package com.backend.projetointegrador.domain.dtos;

import java.time.Instant;

public record ProductRequestDTO(
        String name,
        Instant dueDate,
        Float dailyYield
)
{
}
