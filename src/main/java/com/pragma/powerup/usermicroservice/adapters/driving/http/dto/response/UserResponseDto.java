package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response;

import com.pragma.powerup.usermicroservice.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class UserResponseDto {
    private Long id;
    private String name;
    private String surname;
    private String dniNumber;
    private String phone;
    private LocalDate dateBirthday;
    private String mail;
    private Role id_role;
}
