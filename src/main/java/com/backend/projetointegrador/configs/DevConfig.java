package com.backend.projetointegrador.configs;

import com.backend.projetointegrador.domain.entities.Role;
import com.backend.projetointegrador.domain.entities.User;
import com.backend.projetointegrador.repositories.RoleRepository;
import com.backend.projetointegrador.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = roleRepository.save(new Role(null, "ADMIN"));
        Role clientRole = roleRepository.save(new Role(null, "CLIENT"));

        User u1 = userRepository.save(new User(null, "user1@mail.com", "123456", adminRole));
        User u2 = userRepository.save(new User(null, "user2@mail.com", "123456", adminRole));
        User u3 = userRepository.save(new User(null, "user3@mail.com", "123456", clientRole));
        User u4 = userRepository.save(new User(null, "user4@mail.com", "123456", clientRole));
        User u5 = userRepository.save(new User(null, "user5@mail.com", "123456", clientRole));
        User u6 = userRepository.save(new User(null, "user6@mail.com", "123456", clientRole));
    }
}
