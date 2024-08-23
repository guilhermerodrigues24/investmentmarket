package com.backend.projetointegrador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity(prePostEnabled = true)
public class ProjetoIntegradorSistemasDistribuidosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetoIntegradorSistemasDistribuidosApplication.class, args);
    }

}
