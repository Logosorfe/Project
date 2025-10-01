package com.urlshortener.controller;

import com.urlshortener.dto.AuthRequestDTO;
import com.urlshortener.dto.AuthResponseDTO;
import com.urlshortener.model.User;
import com.urlshortener.security.JwtTokenProvider;
import com.urlshortener.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Operation(summary = "User Registration", description = "Creates a new user in the system")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequestDTO dto) {
        User user = User.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(dto.getRole())
                .build();

        userService.register(user);
        return ResponseEntity.ok("The user has been successfully registered");
    }

    @Operation(summary = "User authentication", description = "Checks email and password, returns a JWT token")
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO dto) {
        Optional<User> userOpt = userService.findByEmail(dto.getEmail());

        if (userOpt.isEmpty() || !passwordEncoder.matches(dto.getPassword(), userOpt.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userOpt.get();
        String token = jwtTokenProvider.generateToken(user.getEmail(), user.getRole());

        return ResponseEntity.ok(new AuthResponseDTO(token));
    }
}
