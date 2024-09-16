package com.backend.projetointegrador.resources;

import com.backend.projetointegrador.domain.dtos.InvestmentRequestDTO;
import com.backend.projetointegrador.domain.dtos.InvestmentResponseDTO;
import com.backend.projetointegrador.services.InvestmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/investment")
public class InvestmentResource {

    private InvestmentService investmentService;

    @GetMapping("/{id}")
    public ResponseEntity<InvestmentResponseDTO> findById(@PathVariable Long id) {
        InvestmentResponseDTO responseDTO = investmentService.findById(id);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping
    public ResponseEntity<InvestmentResponseDTO> create(@RequestBody InvestmentRequestDTO investmentRequestDTO, Authentication authentication) throws URISyntaxException {
        InvestmentResponseDTO responseDTO = investmentService.create(investmentRequestDTO, authentication);
        return ResponseEntity.created(new URI("/investment/" + responseDTO.id())).body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        investmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
