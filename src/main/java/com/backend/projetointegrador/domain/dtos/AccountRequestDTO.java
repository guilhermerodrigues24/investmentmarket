package com.backend.projetointegrador.domain.dtos;

public record AccountRequestDTO(
        String name,
        String document,
        Float balance
) {
}
