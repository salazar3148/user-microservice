package com.pragma.powerup.usermicroservice;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter.UserMysqlAdapter;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.MailAlreadyExistsException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.PersonAlreadyExistsException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IRoleEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IRoleRepository;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepository;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = UserMysqlAdapter.class)
@SpringBootTest
class UserMySqlAdapterTest {
//    @MockBean
//    IUserRepository personRepository;
//
//    @MockBean
//    IUserEntityMapper personEntityMapper;
//
//    @MockBean
//    IRoleEntityMapper roleEntityMapper;
//
//    @MockBean
//    IRoleRepository roleRepository;
//
//    @MockBean
//    PasswordEncoder passwordEncoder;
//
//    @InjectMocks
//    @Autowired
//    UserMysqlAdapter userMysqlAdapter;
//
//    User user;
//
//    @BeforeEach
//    void setUp() {
//        user = new User();
//        user.setName("name");
//        user.setSurname("surname");
//        user.setDniNumber("123456789");
//        user.setPhone("+1234567890");
//        user.setDateBirthday(LocalDate.of(2000, 1, 1));
//        user.setMail("test@example.com");
//        user.setPassword("password");
//    }
//
//    @Test
//    void saveOwnerWithPersonExists() {
//        when(personRepository.findByDniNumber(any())).thenReturn(Optional.of(new UserEntity()));
//        assertThrows(PersonAlreadyExistsException.class, () -> userMysqlAdapter.saveOwner(user));
//    }
//
//    @Test
//    void saveOwnerWithEmailExists() {
//        when(personRepository.findByDniNumber(any())).thenReturn(Optional.empty());
//        when(personRepository.existsByMail(any())).thenReturn(true);
//
//        assertThrows(MailAlreadyExistsException.class, () -> userMysqlAdapter.saveOwner(user));
//    }
//
//    @Test
//    void saveOwner() {
//        when(personRepository.findByDniNumber(any())).thenReturn(Optional.empty());
//        when(personRepository.existsByMail(any())).thenReturn(false);
//        when(passwordEncoder.encode(any())).thenReturn("w68e4r264#!%&%&/%/3513513");
//
//        when(roleRepository.findById(any())).thenReturn(Optional.of(new RoleEntity(3L, "OWNER_ROLE", "OWNER_ROLE")));
//
//        userMysqlAdapter.saveOwner(user);
//        verify(personRepository, times(1)).save(any());
//    }
}
