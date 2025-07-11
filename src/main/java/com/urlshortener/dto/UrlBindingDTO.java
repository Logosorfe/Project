package com.urlshortener.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlBindingDTO {
    private String originalUrl;
    private String shortUrl;
    private Long count;
}
