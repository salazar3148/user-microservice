package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.domain.api.IRoleVerifier;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.UnauthorizedException;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import org.springframework.security.core.AuthenticationException;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IRoleVerifier roleVerifier;

    public UserUseCase(IUserPersistencePort personPersistencePort, IRoleVerifier roleVerifier) {
        this.userPersistencePort = personPersistencePort;
        this.roleVerifier = roleVerifier;
    }

    @Override
    public void saveOwner(User user) {
        userPersistencePort.saveOwner(user);
    }

    @Override
    public Boolean isOwner(String token) {


        if(!roleVerifier.isOwner(token)){
            throw new UnauthorizedException();
        }

        return true;
    }
}
