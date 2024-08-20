package com.backend.projetointegrador.services;

import com.backend.projetointegrador.domain.entities.Role;
import com.backend.projetointegrador.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    Role findByAuthority(String authority) {
        return roleRepository.findByAuthority(authority)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }
}
