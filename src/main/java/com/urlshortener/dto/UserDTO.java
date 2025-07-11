package com.urlshortener.dto;

import com.urlshortener.model.Role;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String email;
    private Role role;
}
