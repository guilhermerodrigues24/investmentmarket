package com.backend.projetointegrador.resources;

import com.backend.projetointegrador.domain.dtos.AccountRequestDTO;
import com.backend.projetointegrador.domain.dtos.AccountResponseDTO;
import com.backend.projetointegrador.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountResource {
    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountResponseDTO>> findAll() {
        List<AccountResponseDTO> accounts = accountService.findAll();
        return ResponseEntity.ok().body(accounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> findById(@PathVariable Long id) {
        AccountResponseDTO responseDTO = accountService.findById(id);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping
    public ResponseEntity<AccountResponseDTO> create(@RequestBody AccountRequestDTO accountRequestDTO) throws URISyntaxException {
        AccountResponseDTO responseDTO = accountService.create(accountRequestDTO);
        return ResponseEntity.created(new URI("/accounts/" + responseDTO.id())).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> update(@PathVariable Long id, @RequestBody AccountRequestDTO requestDTO) {
        AccountResponseDTO responseDTO = accountService.update(id, requestDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }

}