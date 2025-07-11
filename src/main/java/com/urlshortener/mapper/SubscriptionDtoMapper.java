package com.urlshortener.mapper;

import com.urlshortener.dto.SubscriptionDTO;
import com.urlshortener.model.Subscription;

public class SubscriptionDtoMapper {

    public static SubscriptionDTO toDto(Subscription entity) {
        return SubscriptionDTO.builder()
                .creationDate(entity.getCreationDate())
                .expirationDate(entity.getExpirationDate())
                .status(entity.getStatus())
                .build();
    }

    public static Subscription toEntity(SubscriptionDTO dto) {
        return Subscription.builder()
                .creationDate(dto.getCreationDate())
                .expirationDate(dto.getExpirationDate())
                .status(dto.getStatus())
                .build();
    }
}
