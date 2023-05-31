package com.pragma.powerup.usermicroservice.domain.api;

public interface MailExtractor {
    String extractEmail(String token);
}
