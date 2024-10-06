package com.backend.projetointegrador.domain.dtos;

public record UserResponseDTO(
        Long id,
        String email,
        String authority,
        Long accountId
) {
}
