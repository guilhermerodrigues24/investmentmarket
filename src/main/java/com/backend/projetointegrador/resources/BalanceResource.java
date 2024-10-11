package com.backend.projetointegrador.resources;

import com.backend.projetointegrador.domain.dtos.BalanceRequestDTO;
import com.backend.projetointegrador.domain.dtos.BalanceResponseDTO;
import com.backend.projetointegrador.services.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/balance")
@RequiredArgsConstructor
public class BalanceResource {
    private final BalanceService balanceService;

    @GetMapping
    public ResponseEntity<BalanceResponseDTO> findByAccountUser(Authentication authentication) {
        BalanceResponseDTO responseDTO = balanceService.findByAccountUser(authentication);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping
    public ResponseEntity<BalanceResponseDTO> operate(@RequestBody BalanceRequestDTO requestDTO,
                                                      Authentication authentication) {
        BalanceResponseDTO responseDTO = balanceService.operate(authentication, requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }
}
