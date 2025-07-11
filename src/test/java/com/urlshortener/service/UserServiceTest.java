package com.urlshortener.service;

import com.urlshortener.model.Role;
import com.urlshortener.model.User;
import com.urlshortener.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveUser() {
        User user = User.builder()
                .email("test@example.com")
                .password("hashed_pass")
                .role(Role.USER)
                .build();

        User result = userService.register(user);

        assertNotNull(result.getId());
        assertEquals("test@example.com", result.getEmail());
    }
}
