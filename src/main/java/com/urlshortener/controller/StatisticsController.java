package com.urlshortener.controller;

import com.urlshortener.dto.UrlBindingDTO;
import com.urlshortener.mapper.UrlBindingDtoMapper;
import com.urlshortener.model.UrlBinding;
import com.urlshortener.service.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @Operation(summary = "Get the top 10 popular links", description = "Returns a list of the most used shortened links")
    @GetMapping("/top")
    public ResponseEntity<List<UrlBindingDTO>> getTopLinks() {
        List<UrlBinding> topLinks = statisticsService.getTop10PopularLinks();
        List<UrlBindingDTO> dtoList = topLinks.stream()
                .map(UrlBindingDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(dtoList);
    }

    @Operation(summary = "Link statistics by user", description = "Returns a list of UrlBindingDTO by userId")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UrlBindingDTO>> getUserStats(@PathVariable Long userId) {
        List<UrlBinding> stats = statisticsService.getStatsByUser(userId);
        List<UrlBindingDTO> dtoList = stats.stream()
                .map(UrlBindingDtoMapper::toDto)
                .toList();
        return ResponseEntity.ok(dtoList);
    }
}
