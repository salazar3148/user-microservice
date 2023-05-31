package com.pragma.powerup.usermicroservice.domain.usecase;
import com.pragma.powerup.usermicroservice.domain.api.MailExtractor;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final MailExtractor mailExtractor;

    public UserUseCase(IUserPersistencePort personPersistencePort, MailExtractor mailExtractor) {
        this.userPersistencePort = personPersistencePort;
        this.mailExtractor = mailExtractor;
    }

    @Override
    public void saveOwner(User user) {
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
