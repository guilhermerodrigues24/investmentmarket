package com.backend.projetointegrador.domain.dtos;

public record AccountResponseDTO(
        Long id,
        String name,
        String document,
        Float balance
) {
}
