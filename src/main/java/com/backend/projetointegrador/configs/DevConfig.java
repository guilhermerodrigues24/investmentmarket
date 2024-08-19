package com.backend.projetointegrador.configs;

import com.backend.projetointegrador.domain.entities.User;
import com.backend.projetointegrador.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = userRepository.save(new User(null, "user1@mail.com", "123456"));
        User u2 = userRepository.save(new User(null, "user2@mail.com", "123456"));
        User u3 = userRepository.save(new User(null, "user3@mail.com", "123456"));
        User u4 = userRepository.save(new User(null, "user4@mail.com", "123456"));
        User u5 = userRepository.save(new User(null, "user5@mail.com", "123456"));
        User u6 = userRepository.save(new User(null, "user6@mail.com", "123456"));
    }
}
