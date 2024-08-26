package com.backend.projetointegrador.configs;

import com.backend.projetointegrador.domain.entities.Account;
import com.backend.projetointegrador.domain.entities.Role;
import com.backend.projetointegrador.domain.entities.User;
import com.backend.projetointegrador.repositories.AccountRepository;
import com.backend.projetointegrador.repositories.RoleRepository;
import com.backend.projetointegrador.repositories.UserRepository;
import com.backend.projetointegrador.security.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig implements CommandLineRunner {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SecurityConfiguration securityConfiguration;

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = roleRepository.save(new Role(null, "ADMIN"));
        Role clientRole = roleRepository.save(new Role(null, "CLIENT"));

        String password = securityConfiguration.passwordEncoder().encode("123456");

        User u1 = userRepository.save(new User(null, "user1@mail.com", password, adminRole));
        User u2 = userRepository.save(new User(null, "user2@mail.com", password, adminRole));

        User u3 = userRepository.save(new User(null, "user3@mail.com", password, clientRole));
        User u4 = userRepository.save(new User(null, "user4@mail.com", password, clientRole));
        User u5 = userRepository.save(new User(null, "user5@mail.com", password, clientRole));
        User u6 = userRepository.save(new User(null, "user6@mail.com", password, clientRole));

        Account acc1 = accountRepository.save(new Account(null, "Account 1", "123456", 500f, u3));
        Account acc2 = accountRepository.save(new Account(null, "Account 2", "123456", 1000f, u4));
        Account acc3 = accountRepository.save(new Account(null, "Account 3", "123456", 0f, u5));

    }
}
