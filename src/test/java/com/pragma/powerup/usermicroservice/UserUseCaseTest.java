package com.pragma.powerup.usermicroservice;

import com.pragma.powerup.usermicroservice.domain.api.IRoleVerifier;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import com.pragma.powerup.usermicroservice.domain.usecase.UserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

@ContextConfiguration(classes = UserUseCase.class)
@SpringBootTest
public class UserUseCaseTest {
    @MockBean
    IUserPersistencePort usuarioPersistencePort;

    @InjectMocks
    @Autowired
    UserUseCase userUseCase;

    @InjectMocks
    @Autowired
    IRoleVerifier roleVerifier;

    User user;

    @BeforeEach
    public void setUp() {
        userUseCase = new UserUseCase(usuarioPersistencePort, roleVerifier);
        user = new User();
        user.setName("name");
        user.setSurname("surname");
        user.setDniNumber("123456789");
        user.setPhone("+1234567890");
        user.setDateBirthday(LocalDate.of(2000, 1, 1));
        user.setMail("test@example.com");
        user.setPassword("password");
    }

    @Test
    public void saveUser() {
        userUseCase.saveOwner(user);

        verify(usuarioPersistencePort, times(1)).saveOwner(user);
    }
}
