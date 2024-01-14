package ru.wallpost.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import ru.wallpost.DTO.LoginDTO;
import ru.wallpost.DTO.RegisterDTO;


public interface AuthService {

    String SignUp(RegisterDTO registerDTODTO);
    String SignIn(LoginDTO loginDTO);
    static UserDetails getAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            return (UserDetails) auth.getPrincipal();
        } else {
            throw new IllegalStateException("Пользователь не аутентифицирован");
        }
    }
}
