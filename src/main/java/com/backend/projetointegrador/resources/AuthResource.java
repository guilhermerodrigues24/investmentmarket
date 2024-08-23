package com.backend.projetointegrador.resources;

import com.backend.projetointegrador.domain.dtos.UserRequestDTO;
import com.backend.projetointegrador.domain.dtos.UserResponseDTO;
import com.backend.projetointegrador.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthResource {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok().body("Logged in");
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO responseDTO = userService.create(userRequestDTO, "CLIENT");
        return ResponseEntity.ok().body(responseDTO);
    }
}
