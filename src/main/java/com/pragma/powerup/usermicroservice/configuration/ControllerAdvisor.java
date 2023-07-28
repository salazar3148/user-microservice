package com.pragma.powerup.usermicroservice.configuration;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.ConflictException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.NotFoundException;
import com.pragma.powerup.usermicroservice.domain.exceptions.ForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Collections;
import java.util.Map;
import static com.pragma.powerup.usermicroservice.configuration.Constants.RESPONSE_ERROR_MESSAGE_KEY;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> handleAuthenticationException(AuthenticationException authenticationException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, authenticationException.getMessage()));
    }
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Map<String, String>> handleConflictException (
            ConflictException conflictException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, conflictException.getMessage()));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Map<String, String>> handlerForbiddenExceptionException(
            ForbiddenException forbiddenException) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, forbiddenException.getMessage()));
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRoleNotFoundException(
            NotFoundException notFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, notFoundException.getMessage()));
    }
}
