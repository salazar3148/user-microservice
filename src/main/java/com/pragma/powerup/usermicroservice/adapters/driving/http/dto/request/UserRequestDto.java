package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import com.pragma.powerup.usermicroservice.adapters.driving.http.validation.LegalAge;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class UserRequestDto {

    @NotBlank(message = "not null not allowed")
    private String name;

    @NotBlank(message = "not null not allowed")
    private String surname;

    @NotBlank(message = "not null not allowed")
    @Size(min = 8, max = 13)
    @Pattern(regexp="^[0-9]+$", message="numeric field")
    private String dniNumber;

    @NotBlank(message = "not null not allowed")
    @Size(min= 10, max = 13)
    @Pattern(regexp = "^[+]?[0-9]+$", message = "numeric field, (optional) start with the area code of your country")
    private String phone;

    @NotNull
    @LegalAge
    private LocalDate dateBirthday;

    @NotBlank(message = "not null not allowed")
    @Email(message = "mail not valid")
    private String mail;

    @NotBlank(message = "not null not allowed")
    private String password;
}
