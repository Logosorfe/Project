package com.urlshortener.repository;

import com.urlshortener.model.UrlBinding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UrlBindingRepository extends JpaRepository<UrlBinding, Long> {
    Optional<UrlBinding> findByUid(String uid);
    List<UrlBinding> findByUserId(Long userId);

    List<UrlBinding> findTop10ByOrderByCountDesc();
}
