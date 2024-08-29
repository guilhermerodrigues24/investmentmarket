package com.backend.projetointegrador.domain.dtos;

public record ProductTypeResponseDTO(
        Long id,
        String name,
        Float percentage
) {
}
