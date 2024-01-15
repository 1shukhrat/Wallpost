package ru.wallpost.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import ru.wallpost.exception.UserAlreadyExistsException;
import ru.wallpost.util.MessageResponse;

import java.io.IOException;
import java.time.LocalDateTime;

@ControllerAdvice(annotations = {RestController.class})
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<MessageResponse> handleBadCredentialsException() {
        return new ResponseEntity<>(new MessageResponse("Неверный логин или пароль", LocalDateTime.now()),
                HttpStatus.OK);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<MessageResponse> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return new ResponseEntity<>(new MessageResponse(e.getMessage(), LocalDateTime.now()), HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(new MessageResponse(e.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<MessageResponse> handleIOException() {
        return new ResponseEntity<>(new MessageResponse("Ошибка обработки изображений", LocalDateTime.now()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<MessageResponse> handleIllegalStateException() {
        return new ResponseEntity<>(new MessageResponse("Пользователь не авторизован", LocalDateTime.now()),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<MessageResponse> handleUserNotFoundException(UsernameNotFoundException e) {
        return new ResponseEntity<>(new MessageResponse(e.getMessage(), LocalDateTime.now()),HttpStatus.OK);
    }



}
