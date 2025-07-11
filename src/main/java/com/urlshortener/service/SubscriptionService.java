package com.urlshortener.service;

import com.urlshortener.model.Subscription;

import java.util.List;

public interface SubscriptionService {
    Subscription create(Long userId);
    List<Subscription> findByUser(Long userId);
}
