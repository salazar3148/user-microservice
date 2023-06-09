package com.pragma.powerup.usermicroservice.domain.spi;

import com.pragma.powerup.usermicroservice.domain.model.User;

public interface IUserPersistencePort {
    void saveUser(User user);

    User getUser(String mail);

    User getUserById(Long id);
}
