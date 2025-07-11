package com.urlshortener.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate creationDate;
    private LocalDate expirationDate;

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
