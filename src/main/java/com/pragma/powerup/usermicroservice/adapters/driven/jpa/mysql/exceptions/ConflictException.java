package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions;

public class ConflictException extends RuntimeException{
    public ConflictException(String message) {
        super(message);
    }
}
