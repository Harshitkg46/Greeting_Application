package com.example.greetingapp.controller;

import com.example.greetingapp.dto.AuthUserDTO;
import com.example.greetingapp.dto.LoginDTO;
import com.example.greetingapp.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody AuthUserDTO dto) {
        String response = authService.registerUser(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginDTO dto) {
        String token = authService.loginUser(dto);
        return ResponseEntity.ok().body("{\"token\":\"" + token + "\"}");
    }
}