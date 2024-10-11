package com.backend.projetointegrador.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_investment")
public class Investment implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float buyPrice;
    private Instant buyTime;
    private Float sellPrice;
    private Instant sellTime;
    private Boolean isSold = false;

    @ManyToOne(optional = false)
    private Account account;
    @ManyToOne(optional = false)
    private Product product;

    public Investment(Long id, Float buyPrice, Instant buyTime, Account account, Product product) {
        this.id = id;
        this.buyPrice = buyPrice;
        this.buyTime = buyTime;
        this.account = account;
        this.product = product;
    }

    public Investment(Long id, Float buyPrice, Account account, Product product) {
        this(id, buyPrice, Instant.now(), account, product);
    }

    //region equals/hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Investment that = (Investment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
    //endregion
}
