package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.MailAlreadyExistsException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.UserNotFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IRoleEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IRoleRepository;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepository;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.pragma.powerup.usermicroservice.configuration.Constants.OWNER_ROLE_ID;

@RequiredArgsConstructor
public class UserMysqlAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(User user) {

        if (userRepository.existsByMail(user.getMail())){
            throw new MailAlreadyExistsException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public User getUser(String mail) {
        return userEntityMapper.toUser(userRepository.findByMail(mail).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public User getUserById(Long id) {
        return userEntityMapper.toUser(userRepository.findById(id).orElseThrow(UserNotFoundException::new));
    }
}
