package com.pragma.powerup.usermicroservice.domain.exceptions;

public class ForbiddenException extends RuntimeException{
    public ForbiddenException(String message) {
        super(message);
    }
}

