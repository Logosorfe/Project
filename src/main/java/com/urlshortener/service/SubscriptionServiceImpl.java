package com.urlshortener.service;

import com.urlshortener.model.Subscription;
import com.urlshortener.model.SubscriptionStatus;
import com.urlshortener.model.User;
import com.urlshortener.repository.SubscriptionRepository;
import com.urlshortener.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository repository;
    private final UserRepository userRepository;

    @Override
    public Subscription create(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Subscription sub = Subscription.builder()
                .creationDate(LocalDate.now())
                .expirationDate(LocalDate.now().plusMonths(1))
                .status(SubscriptionStatus.PAID)
                .user(user)
                .build();
        return repository.save(sub);
    }

    @Override
    public List<Subscription> findByUser(Long userId) {
        return repository.findByUserId(userId);
    }
}
