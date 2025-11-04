package com.WeBuild.BackWeBuild.controller;

import com.WeBuild.BackWeBuild.dto.AuthResponse;
import com.WeBuild.BackWeBuild.dto.LoginRequest;
import com.WeBuild.BackWeBuild.dto.RegisterRequest;
import com.WeBuild.BackWeBuild.model.Role;
import com.WeBuild.BackWeBuild.model.User;
import com.WeBuild.BackWeBuild.repository.UserRepository;
import com.WeBuild.BackWeBuild.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(AuthResponse.builder().message("E-mail já cadastrado.").build());
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        try {
            user.setRole(Role.valueOf(request.getRole().toUpperCase()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(AuthResponse.builder().message("Cargo inválido. Use CLIENT ou COMPANY.").build());
        }

        userService.save(user);

        return ResponseEntity.ok(AuthResponse.builder()
                .message("Usuário registrado com sucesso.")
                .role(user.getRole().name())
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        // Usa a lógica de segurança do Spring Boot (Basic Auth)
        try {
            UserDetails userDetails = userService.loadUserByUsername(request.getEmail());
            if (passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
                return ResponseEntity.ok(AuthResponse.builder()
                        .token("SESSION_TOKEN_MOCK") // Token simulado
                        .role(userDetails.getAuthorities().iterator().next().getAuthority())
                        .message("Login bem-sucedido.")
                        .build());
            }
        } catch (Exception e) {
            // Log de erro
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(AuthResponse.builder().message("Credenciais inválidas.").build());
    }
}