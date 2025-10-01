package com.urlshortener.repository;

import com.urlshortener.model.UrlBinding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UrlBindingRepository extends JpaRepository<UrlBinding, Long> {
    Optional<UrlBinding> findByUid(String uid);
    List<UrlBinding> findByUserId(Long userId);

    @Query(value = "SELECT * FROM url_binding ORDER BY count DESC LIMIT 10", nativeQuery = true)
    List<UrlBinding> findTop10();
}
