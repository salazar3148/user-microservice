package com.pragma.powerup.usermicroservice.domain.usecase;
import com.pragma.powerup.usermicroservice.domain.api.MailExtractor;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IRolePersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;

import static com.pragma.powerup.usermicroservice.configuration.Constants.OWNER_ROLE_ID;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IRolePersistencePort rolePersistencePort;
    private final MailExtractor mailExtractor;

    public UserUseCase(IUserPersistencePort personPersistencePort, IRolePersistencePort rolePersistencePort, MailExtractor mailExtractor) {
        this.userPersistencePort = personPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
        this.mailExtractor = mailExtractor;
    }

    @Override
    public void saveOwner(User user) {
        user.setRole(
             rolePersistencePort.getRole(OWNER_ROLE_ID)
        );
        userPersistencePort.saveOwner(user);
    }

    @Override
    public User getUser(String token) {
        return userPersistencePort.getUser(mailExtractor.extractEmail(token));
    }

    @Override
    public User getUserById(Long id) {
        return userPersistencePort.getUserById(id);
    }
}
