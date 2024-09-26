package com.backend.projetointegrador.services;

import com.backend.projetointegrador.domain.dtos.ProductRequestDTO;
import com.backend.projetointegrador.domain.dtos.ProductResponseDTO;
import com.backend.projetointegrador.domain.entities.Product;
import com.backend.projetointegrador.domain.mappers.ProductMapper;
import com.backend.projetointegrador.repositories.ProductRepository;
import com.backend.projetointegrador.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll().stream()
                .map(entity -> ProductMapper.toResponseDTO(entity)).toList();
    }

    public ProductResponseDTO findById(Long id) {
        Product product = findEntityById(id);
        return ProductMapper.toResponseDTO(product);
    }

    public ProductResponseDTO create(ProductRequestDTO requestDTO) {
        Product product = new Product();
        addDTOValuesToEntity(product, requestDTO);
        product = productRepository.save(product);

        return ProductMapper.toResponseDTO(product);
    }

    public ProductResponseDTO update(Long id, ProductRequestDTO requestDTO) {
        Product product = findEntityById(id);
        addDTOValuesToEntity(product, requestDTO);
        product = productRepository.save(product);

        return ProductMapper.toResponseDTO(product);
    }

    public void delete(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(Product.class, id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    Product findEntityById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Product.class, id));
    }

    Float getYieldPercentageInDateRange(Product product, Instant buyTime, Instant sellTime) {
        sellTime = product.getDueDate().isBefore(sellTime) ? product.getDueDate() : sellTime;
        Long days = ChronoUnit.DAYS.between(buyTime, sellTime);
        return product.getDailyYield() * days;
    }

    private void addDTOValuesToEntity(Product product, ProductRequestDTO requestDTO) {

        product.setName(requestDTO.name());
        product.setDueDate(requestDTO.dueDate());
        product.setDailyYield(requestDTO.dailyYield());
    }

}
