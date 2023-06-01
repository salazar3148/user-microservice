package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserRequestMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserResponseMapper;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHandlerImpl implements IUserHandler {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;

    @Override
    public void saveOwner(String token, UserRequestDto userRequestDto) {
        userServicePort.saveOwner(token, userRequestMapper.toUser(userRequestDto));
    }

    @Override
    public void saveEmployee(String token, UserRequestDto userRequestDto) {
        userServicePort.saveEmployee(token, userRequestMapper.toUser(userRequestDto));
    }

    @Override
    public UserResponseDto getUser(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        return userResponseMapper.userToUserResponseDto(userServicePort.getUser(token));
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        return userResponseMapper.userToUserResponseDto(userServicePort.getUserById(id));
    }
}
