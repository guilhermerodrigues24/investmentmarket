package com.backend.projetointegrador.resources;

import com.backend.projetointegrador.domain.dtos.ProductRequestDTO;
import com.backend.projetointegrador.domain.dtos.ProductResponseDTO;
import com.backend.projetointegrador.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/products")
public class ProductResource {

    @Autowired
    private ProductService service;
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id) {
        ProductResponseDTO responseDTO = productService.findById(id);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@RequestBody ProductRequestDTO productRequestDTO) throws URISyntaxException {
        ProductResponseDTO responseDTO = productService.create(productRequestDTO);
        return ResponseEntity.created(new URI("/products/" + responseDTO.id())).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, @RequestBody ProductRequestDTO requestDTO) {
        ProductResponseDTO responseDTO = productService.update(id, requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
