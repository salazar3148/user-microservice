package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.domain.model.User;

public interface IUserServicePort {
    void saveOwner(String token, User user);
    User getUser(String token);
    User getUserById(Long id);
}
