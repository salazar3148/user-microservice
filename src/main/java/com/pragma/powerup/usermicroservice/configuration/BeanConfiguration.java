package com.pragma.powerup.usermicroservice.configuration;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.pragma.powerup.usermicroservice.adapters.driven.cognito.CognitoAdapter;
import com.pragma.powerup.usermicroservice.adapters.driven.cognito.UserCognitoDtoMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter.RoleMysqlAdapter;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter.UserMysqlAdapter;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IRoleEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IRoleRepository;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepository;
import com.pragma.powerup.usermicroservice.adapters.driving.http.jwt.JwtMailExtractor;
import com.pragma.powerup.usermicroservice.configuration.aws.AwsCognitoProviderConfig;
import com.pragma.powerup.usermicroservice.configuration.security.jwt.JwtProvider;
import com.pragma.powerup.usermicroservice.domain.api.IRoleServicePort;
import com.pragma.powerup.usermicroservice.domain.api.MailExtractor;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.spi.ICognitoServicePort;
import com.pragma.powerup.usermicroservice.domain.spi.IRolePersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import com.pragma.powerup.usermicroservice.domain.usecase.RoleUseCase;
import com.pragma.powerup.usermicroservice.domain.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IRoleRepository roleRepository;
    private final IUserRepository userRepository;
    private final IRoleEntityMapper roleEntityMapper;
    private final IUserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;

    private final AWSCognitoIdentityProvider awsCognitoIdentityProvider;
    private final UserCognitoDtoMapper userCognitoDtoMapper;

    private final JwtProvider jwtProvider;

    @Bean
    public IRoleServicePort roleServicePort() {
        return new RoleUseCase(rolePersistencePort());
    }
    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new RoleMysqlAdapter(roleRepository, roleEntityMapper);
    }
    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserMysqlAdapter(userRepository, userEntityMapper, passwordEncoder);
    }

    @Bean
    public MailExtractor roleVerifier(){
        return new JwtMailExtractor(jwtProvider);
    }
    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(), rolePersistencePort(), cognitoServicePort(), roleVerifier());
    }
    @Bean
    public ICognitoServicePort cognitoServicePort(){
        return new CognitoAdapter(awsCognitoIdentityProvider, userCognitoDtoMapper);
    }
}
