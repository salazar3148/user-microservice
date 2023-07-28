package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserResponseDto {
    private Long id;
    private Long idRole;
    private String phone;
    private String mail;
}
