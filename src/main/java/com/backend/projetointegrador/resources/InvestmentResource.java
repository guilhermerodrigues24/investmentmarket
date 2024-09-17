package com.backend.projetointegrador.resources;

import com.backend.projetointegrador.domain.dtos.InvestmentRequestDTO;
import com.backend.projetointegrador.domain.dtos.InvestmentResponseDTO;
import com.backend.projetointegrador.services.InvestmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/investments")
@RequiredArgsConstructor
public class InvestmentResource {
    private final InvestmentService investmentService;

    @GetMapping
    public ResponseEntity<List<InvestmentResponseDTO>> findAll() {
        return ResponseEntity.ok().body(investmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestmentResponseDTO> findById(@PathVariable Long id) {
        InvestmentResponseDTO responseDTO = investmentService.findById(id);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping
    public ResponseEntity<InvestmentResponseDTO> create(@RequestBody InvestmentRequestDTO investmentRequestDTO, Authentication authentication) throws URISyntaxException {
        InvestmentResponseDTO responseDTO = investmentService.create(investmentRequestDTO, authentication);
        return ResponseEntity.created(new URI("/investments/" + responseDTO.id())).body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        investmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
