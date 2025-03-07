package com.example.greetingapp.service;

import com.example.greetingapp.dto.AuthUserDTO;
import com.example.greetingapp.dto.LoginDTO;
import com.example.greetingapp.exception.EmailAlreadyExistsException;
import com.example.greetingapp.exception.InvalidLoginException;
import com.example.greetingapp.model.AuthUser;
import com.example.greetingapp.repository.AuthUserRepository;
import com.example.greetingapp.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(AuthUserRepository repository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String registerUser(AuthUserDTO dto) {
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists: " + dto.getEmail());
        }

        AuthUser user = new AuthUser();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        repository.save(user);

        return "User registered successfully!";
    }

    public String loginUser(LoginDTO dto) {
        AuthUser user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new InvalidLoginException("Invalid email or password!"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new InvalidLoginException("Invalid email or password!");
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}