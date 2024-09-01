package com.backend.projetointegrador.services;

import com.backend.projetointegrador.domain.dtos.ProductTypeRequestDTO;
import com.backend.projetointegrador.domain.dtos.ProductTypeResponseDTO;
import com.backend.projetointegrador.domain.entities.ProductType;
import com.backend.projetointegrador.domain.mappers.ProductTypeMapper;
import com.backend.projetointegrador.repositories.ProductTypeRepository;
import com.backend.projetointegrador.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductTypeService {
    private final ProductTypeRepository productTypeRepository;

    public List<ProductTypeResponseDTO> findAll() {
        return productTypeRepository.findAll().stream()
                .map(entity -> ProductTypeMapper.toResponseDTO(entity)).toList();
    }

    public ProductTypeResponseDTO findById(Long id) {
        ProductType productType = findEntityById(id);
        return ProductTypeMapper.toResponseDTO(productType);
    }

    @Transactional
    public ProductTypeResponseDTO create(ProductTypeRequestDTO requestDTO) {
        ProductType productType = new ProductType();
        productType.setName(requestDTO.name());
        productType.setPercentage(requestDTO.percentage());

        productType = productTypeRepository.save(productType);
        return ProductTypeMapper.toResponseDTO(productType);
    }

    @Transactional
    public ProductTypeResponseDTO update(Long id, ProductTypeRequestDTO requestDTO) {
        ProductType productType = findEntityById(id);

        productType.setName(requestDTO.name());
        productType.setPercentage(requestDTO.percentage());
        productType = productTypeRepository.save(productType);

        return ProductTypeMapper.toResponseDTO(productType);
    }

    public void delete(Long id) {
        // TODO check if there are products with this type and throw an exception
        try {
            productTypeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(ProductType.class, id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    ProductType findEntityById(Long id) {
        return productTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ProductType.class, id));
    }

}
