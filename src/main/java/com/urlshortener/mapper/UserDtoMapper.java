package com.urlshortener.mapper;

import com.urlshortener.dto.UserDTO;
import com.urlshortener.model.User;

public class UserDtoMapper {

    public static UserDTO toDto(User user) {
        return UserDTO.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public static User toEntity(UserDTO dto) {
        return User.builder()
                .email(dto.getEmail())
                .role(dto.getRole())
                .build();
    }
}
