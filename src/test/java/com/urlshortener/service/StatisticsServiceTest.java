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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
public class StatisticsServiceTest {

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private UrlBindingRepository urlBindingRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldReturnTop10Bindings() {
        User user = userRepository.save(User.builder()
                .email("user@example.com")
                .password("password")
                .role(Role.USER)
                .build());

        for (int i = 0; i < 15; i++) {
            urlBindingRepository.save(UrlBinding.builder()
                    .originalUrl("http://site" + i + ".com")
                    .baseUrl("http://short.ly")
                    .uid("link" + i)
                    .user(user)
                    .count((long) (15 - i))
                    .build());
        }

        List<UrlBinding> top = statisticsService.getTop10PopularLinks();
        assertEquals(10, top.size());
        assertTrue(top.get(0).getCount() >= top.get(9).getCount());
    }
}
