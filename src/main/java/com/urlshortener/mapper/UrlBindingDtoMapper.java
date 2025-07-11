package com.urlshortener.mapper;

import com.urlshortener.dto.UrlBindingDTO;
import com.urlshortener.model.UrlBinding;

public class UrlBindingDtoMapper {

    public static UrlBindingDTO toDto(UrlBinding entity) {
        String shortUrl = entity.getBaseUrl() + "/" + entity.getUid();
        return UrlBindingDTO.builder()
                .originalUrl(entity.getOriginalUrl())
                .shortUrl(shortUrl)
                .count(entity.getCount())
                .build();
    }

    public static UrlBinding toEntity(UrlBindingDTO dto) {
        String[] parts = dto.getShortUrl().split("/");
        String uid = parts[parts.length - 1];
        String baseUrl = dto.getShortUrl().substring(0, dto.getShortUrl().lastIndexOf("/"));

        return UrlBinding.builder()
                .originalUrl(dto.getOriginalUrl())
                .baseUrl(baseUrl)
                .uid(uid)
                .count(dto.getCount())
                .build();
    }
}
