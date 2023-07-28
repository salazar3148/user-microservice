package com.pragma.powerup.usermicroservice.domain.spi;
import com.pragma.powerup.usermicroservice.domain.model.User;

public interface ICognitoServicePort {
    void signUpUser(User user);
}
