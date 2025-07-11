package com.urlshortener.mapper;

import com.urlshortener.dto.UserDTO;
import com.urlshortener.model.Role;
import com.urlshortener.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDtoMapperTest {

    @Test
    void toDto_shouldMapCorrectly() {
        User user = User.builder()
                .email("a@b.com")
                .role(Role.USER)
                .build();

        UserDTO dto = UserDtoMapper.toDto(user);
        assertEquals("a@b.com", dto.getEmail());
    }

    @Test
    void toEntity_shouldMapCorrectly() {
        UserDTO dto = new UserDTO("a@b.com", Role.USER);
        User user = UserDtoMapper.toEntity(dto);
        assertEquals("a@b.com", user.getEmail());
    }
}
