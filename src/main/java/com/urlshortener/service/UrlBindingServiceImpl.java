package com.urlshortener.service;

import com.urlshortener.model.UrlBinding;
import com.urlshortener.model.User;
import com.urlshortener.repository.UrlBindingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UrlBindingServiceImpl implements UrlBindingService {

    private final UrlBindingRepository repository;

    @Override
    public UrlBinding create(UrlBinding binding) {
        return repository.save(binding);
    }

    @Override
    public Optional<UrlBinding> findByUid(String uid) {
        return repository.findByUid(uid);
    }

    @Override
    public List<UrlBinding> findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public void update(UrlBinding binding) {
        repository.save(binding);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UrlBinding createBinding(String originalUrl, String baseUrl, User user) {
        String uid = UUID.randomUUID().toString().substring(0, 8);

        UrlBinding binding = UrlBinding.builder()
                .originalUrl(originalUrl)
                .baseUrl(baseUrl)
                .uid(uid)
                .user(user)
                .count(0L)
                .build();

        return repository.save(binding);
    }

    @Override
    public void incrementCount(String uid) {
        UrlBinding binding = repository.findByUid(uid)
                .orElseThrow(() -> new RuntimeException("Binding not found for uid: " + uid));

        binding.setCount(binding.getCount() + 1);
        repository.save(binding);
    }

}
