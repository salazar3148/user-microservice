package com.pragma.powerup.usermicroservice.domain.api;

public interface IRoleVerifier {
    boolean isOwner(String token);

}
