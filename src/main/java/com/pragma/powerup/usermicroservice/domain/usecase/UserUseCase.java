package com.pragma.powerup.usermicroservice.domain.usecase;
import com.pragma.powerup.usermicroservice.domain.api.MailExtractor;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.ForbiddenException;
import com.pragma.powerup.usermicroservice.domain.exceptions.UnauthorizedException;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.ICognitoServicePort;
import com.pragma.powerup.usermicroservice.domain.spi.IRolePersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;

import static com.pragma.powerup.usermicroservice.configuration.Constants.ADMIN_ROLE_ID;
import static com.pragma.powerup.usermicroservice.configuration.Constants.CUSTOMER_ROLE_ID;
import static com.pragma.powerup.usermicroservice.configuration.Constants.EMPLOYEE_ROLE_ID;
import static com.pragma.powerup.usermicroservice.configuration.Constants.OWNER_ROLE_ID;
import static com.pragma.powerup.usermicroservice.configuration.Constants.ROLE_NOT_ALLOWED_MESSAGE;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IRolePersistencePort rolePersistencePort;
    private final ICognitoServicePort cognitoServicePort;
    private final MailExtractor mailExtractor;

    public UserUseCase(IUserPersistencePort userPersistencePort, IRolePersistencePort rolePersistencePort, ICognitoServicePort cognitoServicePort, MailExtractor mailExtractor) {
        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
        this.cognitoServicePort = cognitoServicePort;
        this.mailExtractor = mailExtractor;
    }

    @Override
    public void saveOwner(String token, User user) {
        User adminUser = getUser(token);

        if(!adminUser.getRole().getId().equals(ADMIN_ROLE_ID)){
            throw new ForbiddenException(ROLE_NOT_ALLOWED_MESSAGE);
        }

        user.setRole(
             rolePersistencePort.getRole(OWNER_ROLE_ID)
        );

        userPersistencePort.saveUser(user);
        cognitoServicePort.signUpUser(user);
    }

    @Override
    public void saveEmployee(String token, User user) {
        User ownerUser = getUser(token);

        if(!ownerUser.getRole().getId().equals(OWNER_ROLE_ID)){
            throw new UnauthorizedException(ROLE_NOT_ALLOWED_MESSAGE);
        }

        user.setRole(
                rolePersistencePort.getRole(EMPLOYEE_ROLE_ID)
        );

        userPersistencePort.saveUser(user);
        cognitoServicePort.signUpUser(user);
    }

    @Override
    public void saveCustomer(User user) {
        user.setRole(
                rolePersistencePort.getRole(CUSTOMER_ROLE_ID)
        );

        cognitoServicePort.signUpUser(user);
        userPersistencePort.saveUser(user);
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
