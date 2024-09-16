package com.backend.projetointegrador.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_account")
public class Account implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String document;
    @Setter(lombok.AccessLevel.NONE)
    private Float balance;

    @OneToOne
    private User user;

    public Account(Long id, String name, String document, Float balance, User user) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.balance = balance;
        this.user = user;
    }

    public void addBalance(Float value) {
        this.balance += value;
    }

    public void subtractBalance(Float value) {
        if (this.balance < value) {
            throw new RuntimeException("Insufficient balance");
        }
        this.balance -= value;
    }
}
