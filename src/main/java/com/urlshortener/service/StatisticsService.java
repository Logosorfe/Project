package com.urlshortener.service;

import com.urlshortener.model.UrlBinding;
import com.urlshortener.model.User;

import java.util.List;

public interface StatisticsService {
    List<UrlBinding> getTop10PopularLinks();
    List<User> getAllUsersWithStats();
    List<UrlBinding> getStatsByUser(Long userId);
}
