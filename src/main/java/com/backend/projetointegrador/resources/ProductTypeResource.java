package com.backend.projetointegrador.resources;

import com.backend.projetointegrador.domain.dtos.ProductTypeRequestDTO;
import com.backend.projetointegrador.domain.dtos.ProductTypeResponseDTO;
import com.backend.projetointegrador.services.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductTypeResource {
    private final ProductTypeService productTypeService;

    @GetMapping
    public ResponseEntity<List<ProductTypeResponseDTO>> findAll() {
        List<ProductTypeResponseDTO> products = productTypeService.findAll();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductTypeResponseDTO> findById(@PathVariable Long id) {
        ProductTypeResponseDTO responseDTO = productTypeService.findById(id);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping
    public ResponseEntity<ProductTypeResponseDTO> create(@RequestBody ProductTypeRequestDTO productTypeRequestDTO) throws URISyntaxException {
        ProductTypeResponseDTO responseDTO = productTypeService.create(productTypeRequestDTO);
        return ResponseEntity.created(new URI("/products/" + responseDTO.id())).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductTypeResponseDTO> update(@PathVariable Long id, @RequestBody ProductTypeRequestDTO requestDTO) {
        ProductTypeResponseDTO responseDTO = productTypeService.update(id, requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}