package ru.wallpost.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import ru.wallpost.exception.UserAlreadyExistsException;
import ru.wallpost.util.AuthResponse;
import ru.wallpost.util.MessageResponse;

import java.util.Date;

@ControllerAdvice(annotations = {RestController.class})
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<AuthResponse> handleBadCredentialsException(BadCredentialsException e) {
        return new ResponseEntity<>(new AuthResponse("Неверный логин или пароль", null, new Date()), HttpStatus.OK);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<MessageResponse> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return new ResponseEntity<>(new MessageResponse(e.getMessage(), new Date()), HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(new MessageResponse(e.getMessage(), new Date()), HttpStatus.BAD_REQUEST);
    }

}
