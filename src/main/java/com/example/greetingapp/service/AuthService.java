package com.example.greetingapp.service;

import com.example.greetingapp.dto.AuthUserDTO;
import com.example.greetingapp.exception.EmailAlreadyExistsException;
import com.example.greetingapp.model.AuthUser;
import com.example.greetingapp.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AuthUserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(AuthUserDTO dto) {
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists: " + dto.getEmail());
        }

        AuthUser user = new AuthUser();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));  // Encrypting password here

        repository.save(user);

        return "User registered successfully!";
    }
}