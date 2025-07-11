package com.urlshortener.service;

import com.urlshortener.model.User;

import java.util.Optional;

public interface UserService {
    User register(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    void delete(Long id);
}
