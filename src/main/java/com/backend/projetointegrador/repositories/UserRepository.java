package com.backend.projetointegrador.repositories;

import com.backend.projetointegrador.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
