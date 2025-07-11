package com.urlshortener.service;

import com.urlshortener.model.Role;
import com.urlshortener.model.UrlBinding;
import com.urlshortener.model.User;
import com.urlshortener.repository.UrlBindingRepository;
import com.urlshortener.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase
public class UrlBindingServiceTest {

    @Autowired
    private UrlBindingService urlBindingService;

    @Autowired
    private UrlBindingRepository urlBindingRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldCreateUrlBindingAndIncrementCount() {
        User user = userRepository.save(User.builder()
                .email("tester@site.com")
                .password("12345")
                .role(Role.USER)
                .build());

        UrlBinding created = urlBindingService.createBinding(
                "http://example.com", "http://short.ly", user);

        assertNotNull(created.getUid());
        assertEquals(0, created.getCount());

        urlBindingService.incrementCount(created.getUid());
        UrlBinding updated = urlBindingRepository.findByUid(created.getUid()).orElseThrow();
        assertEquals(1, updated.getCount());
    }
}
