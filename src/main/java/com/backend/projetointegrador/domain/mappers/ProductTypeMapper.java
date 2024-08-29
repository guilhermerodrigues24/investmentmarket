package com.backend.projetointegrador.domain.mappers;

import com.backend.projetointegrador.domain.dtos.ProductTypeResponseDTO;
import com.backend.projetointegrador.domain.entities.ProductType;

public class ProductTypeMapper {
    public static ProductTypeResponseDTO toResponseDTO(ProductType productType) {
        return new ProductTypeResponseDTO(productType.getId(),
                productType.getName(),
                productType.getPercentage()
        );
    }
}
