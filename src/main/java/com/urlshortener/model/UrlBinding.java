package com.urlshortener.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UrlBinding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalUrl;
    private String baseUrl;
    private String pathPrefix;
    private String uid;

    private Long count;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
