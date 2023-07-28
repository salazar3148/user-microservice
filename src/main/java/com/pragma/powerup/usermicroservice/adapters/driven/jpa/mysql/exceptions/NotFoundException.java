package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}