package com.urlshortener.dto;

import com.urlshortener.model.SubscriptionStatus;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDTO {
    private LocalDate creationDate;
    private LocalDate expirationDate;
    private SubscriptionStatus status;
}
