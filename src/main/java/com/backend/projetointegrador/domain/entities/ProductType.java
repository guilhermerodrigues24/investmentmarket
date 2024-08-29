package com.backend.projetointegrador.domain.entities;

import com.backend.projetointegrador.services.exceptions.InvalidArgsException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_product_type")
public class ProductType implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Float percentage;

    public void setPercentage(Float percentage) {
        if (percentage < 0f || percentage > 1f) {
            throw new InvalidArgsException(ProductType.class, "Percentage must be between 0 and 1. Actual: " + percentage);
        }
        this.percentage = percentage;
    }
}
