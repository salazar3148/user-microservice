package com.pragma.powerup.usermicroservice.adapters.driven.cognito;

import com.pragma.powerup.usermicroservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserCognitoDtoMapper {
    UserCognitoDto userToUserCognitoDto(User user);
}
