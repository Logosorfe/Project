package com.urlshortener.controller;

import com.urlshortener.dto.UserDTO;
import com.urlshortener.mapper.UserDtoMapper;
import com.urlshortener.model.User;
import com.urlshortener.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "User registration via DTO", description = "Allows creating a new user")
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO dto) {
        User user = UserDtoMapper.toEntity(dto);           // ручной маппинг
        User saved = userService.register(user);
        return ResponseEntity.ok(UserDtoMapper.toDto(saved));
    }

    @Operation(summary = "Get user by email", description = "Returns the user DTO by email")
    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getByEmail(@PathVariable String email) {
        return userService.findByEmail(email)
                .map(UserDtoMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
