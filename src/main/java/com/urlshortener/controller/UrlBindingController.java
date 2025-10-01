package com.urlshortener.controller;

import com.urlshortener.dto.UrlBindingDTO;
import com.urlshortener.mapper.UrlBindingDtoMapper;
import com.urlshortener.model.UrlBinding;
import com.urlshortener.service.UrlBindingService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bindings")
@RequiredArgsConstructor
public class UrlBindingController {

    private final UrlBindingService urlBindingService;

    @Operation(summary = "Create a short link", description = "Accepts a DTO and returns the saved pair")
    @PostMapping
    public ResponseEntity<UrlBindingDTO> create(@RequestBody UrlBindingDTO dto) {
        UrlBinding entity = UrlBindingDtoMapper.toEntity(dto);
        UrlBinding saved = urlBindingService.create(entity);
        UrlBindingDTO response = UrlBindingDtoMapper.toDto(saved);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all user URL bindings", description = "Returns a list of UrlBindingDTO by userId")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UrlBindingDTO>> getUserBindings(@PathVariable Long userId) {
        List<UrlBinding> list = urlBindingService.findByUserId(userId);
        List<UrlBindingDTO> dtoList = list.stream()
                .map(UrlBindingDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(dtoList);
    }

    @Operation(summary = "Get link by UID", description = "Searches UrlBinding by unique identifier")
    @GetMapping("/{uid}")
    public ResponseEntity<UrlBindingDTO> getByUid(@PathVariable String uid) {
        return urlBindingService.findByUid(uid)
                .map(UrlBindingDtoMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
