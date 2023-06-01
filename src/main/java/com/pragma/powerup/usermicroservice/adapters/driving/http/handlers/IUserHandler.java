package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;

public interface IUserHandler {
    void saveOwner(String token, UserRequestDto userRequestDto);
    void saveEmployee(String token, UserRequestDto userRequestDto);
    void saveCustomer(UserRequestDto userRequestDto);
    UserResponseDto getUser(String token);
    UserResponseDto getUserById(Long id);
}
