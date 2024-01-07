package ru.wallpost.util;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String username) {
        super("Имя пользователя " + username + " занято");
    }
}
