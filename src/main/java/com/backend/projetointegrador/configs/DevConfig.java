package com.backend.projetointegrador.configs;

import com.backend.projetointegrador.domain.entities.Account;
import com.backend.projetointegrador.domain.entities.Product;
import com.backend.projetointegrador.domain.entities.ProductType;
import com.backend.projetointegrador.domain.entities.Role;
import com.backend.projetointegrador.domain.entities.User;
import com.backend.projetointegrador.repositories.AccountRepository;
import com.backend.projetointegrador.repositories.ProductRepository;
import com.backend.projetointegrador.repositories.ProductTypeRepository;
import com.backend.projetointegrador.repositories.RoleRepository;
import com.backend.projetointegrador.repositories.UserRepository;
import com.backend.projetointegrador.security.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;

@Configuration
@Profile("dev")
public class DevConfig implements CommandLineRunner {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductTypeRepository productTypeRepository;
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

        ProductType pt1 = productTypeRepository.save(new ProductType(null, "Investment", 0.1f));
        ProductType pt2 = productTypeRepository.save(new ProductType(null, "Poupança", 0.05f));
        ProductType pt3 = productTypeRepository.save(new ProductType(null, "Pix Buzzard", 0.2f));

        //TODO Add a util to generate future dates
        Product p1 = productRepository.save(new Product(null, "Pix Buzzard 30 dias", 184f, Instant.now(), pt3));
        Product p2 = productRepository.save(new Product(null, "Pix Buzzard 60 dias", 23f, Instant.now(), pt3));
        Product p3 = productRepository.save(new Product(null, "Pix Buzzard 90 dias", 250f, Instant.now(), pt3));
        Product p4 = productRepository.save(new Product(null, "Poupança programada 360 dias", 100f, Instant.now(), pt2));
    }
}
