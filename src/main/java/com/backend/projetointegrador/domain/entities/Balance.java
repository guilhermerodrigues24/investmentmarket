package com.backend.projetointegrador.domain.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_balance")
public class Balance implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter(AccessLevel.NONE)
    private Float amount;

    @OneToOne
    private Account account;
    @OneToMany(mappedBy = "balance")
    private List<Transaction> transactions;

    public Balance(Float amount, Account account) {
        this.amount = amount;
        this.account = account;
    }


    //region equals/hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Balance balance = (Balance) o;
        return Objects.equals(id, balance.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
    //endregion

    public void addBalance(Float value) {
        this.amount += value;
    }

    public void subtractBalance(Float value) {
        if (this.amount < value) {
            throw new RuntimeException("Insufficient balance");
        }
        this.amount -= value;
    }
}
