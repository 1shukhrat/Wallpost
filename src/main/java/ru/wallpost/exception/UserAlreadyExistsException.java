package ru.wallpost.exception;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String username) {
        super("Имя пользователя " + username + " занято");
    }
}
