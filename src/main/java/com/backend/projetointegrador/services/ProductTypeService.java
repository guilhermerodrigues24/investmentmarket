package com.backend.projetointegrador.services;

import com.backend.projetointegrador.domain.entities.ProductType;
import com.backend.projetointegrador.repositories.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeService {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    public List<ProductType> findAll() {
        return productTypeRepository.findAll();
    }

    public ProductType findById(Long id) {
        Optional<ProductType> productType = productTypeRepository.findById(id);
        return productType.orElse(null);
    }

    @Transactional
    public ProductType create(ProductType productType) {
        productType = productTypeRepository.save(productType);
        return productType;
    }

    @Transactional
    public ProductType update(ProductType productType) {
        ProductType newProductType = findById(productType.getId());
        newProductType.setName(productType.getName());
        return productTypeRepository.save(newProductType);
    }

    public Optional<ProductType> deleteById(Long id) {
        findById(id);
        try {
            productTypeRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("");
        }
        return productTypeRepository.findById(id);
    }


}
