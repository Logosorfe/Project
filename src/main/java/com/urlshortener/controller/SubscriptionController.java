package com.urlshortener.controller;

import com.urlshortener.dto.SubscriptionDTO;
import com.urlshortener.mapper.SubscriptionDtoMapper;
import com.urlshortener.model.Subscription;
import com.urlshortener.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Operation(summary = "Create a subscription for the user", description = "Registers a new subscription by userId")
    @PostMapping("/{userId}")
    public ResponseEntity<SubscriptionDTO> create(@PathVariable Long userId) {
        Subscription subscription = subscriptionService.create(userId);
        return ResponseEntity.ok(SubscriptionDtoMapper.toDto(subscription));
    }

    @Operation(summary = "Get all user subscriptions", description = "Returns a list of subscriptions by userId")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SubscriptionDTO>> getUserSubscriptions(@PathVariable Long userId) {
        List<Subscription> list = subscriptionService.findByUser(userId);
        List<SubscriptionDTO> dtoList = list.stream()
                .map(SubscriptionDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(dtoList);
    }
}
