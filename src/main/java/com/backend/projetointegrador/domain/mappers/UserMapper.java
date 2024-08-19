package com.backend.projetointegrador.domain.mappers;

import com.backend.projetointegrador.domain.dtos.UserResponseDTO;
import com.backend.projetointegrador.domain.entities.User;

public class UserMapper {
    public static UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(user.getId(), user.getEmail());
    }
}
