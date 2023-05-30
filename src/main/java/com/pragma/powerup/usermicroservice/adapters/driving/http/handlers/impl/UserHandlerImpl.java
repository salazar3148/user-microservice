package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserRequestMapper;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHandlerImpl implements IUserHandler {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;

    @Override
    public void saveOwner(UserRequestDto userRequestDto) {
        userServicePort.saveOwner(userRequestMapper.toUser(userRequestDto));
    }

    @Override
    public Boolean isOwner(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        
        return userServicePort.isOwner(token);
    }
}
