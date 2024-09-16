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
}
