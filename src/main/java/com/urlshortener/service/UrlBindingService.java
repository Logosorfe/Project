package com.urlshortener.service;

import com.urlshortener.model.UrlBinding;
import com.urlshortener.model.User;

import java.util.List;
import java.util.Optional;

public interface UrlBindingService {
    UrlBinding create(UrlBinding binding);
    Optional<UrlBinding> findByUid(String uid);
    List<UrlBinding> findByUserId(Long userId);
    void update(UrlBinding binding);
    void delete(Long id);

    UrlBinding createBinding(String url, String url1, User user);

    void incrementCount(String uid);
}
