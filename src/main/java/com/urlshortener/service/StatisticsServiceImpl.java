package com.urlshortener.service;

import com.urlshortener.model.UrlBinding;
import com.urlshortener.model.User;
import com.urlshortener.repository.UrlBindingRepository;
import com.urlshortener.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final UrlBindingRepository urlBindingRepository;
    private final UserRepository userRepository;

    @Override
    public List<UrlBinding> getTop10PopularLinks() {
        return urlBindingRepository.findTop10();
    }

    @Override
    public List<User> getAllUsersWithStats() {
        return userRepository.findAll();
    }

    @Override
    public List<UrlBinding> getStatsByUser(Long userId) {
        return urlBindingRepository.findByUserId(userId);
    }
}
