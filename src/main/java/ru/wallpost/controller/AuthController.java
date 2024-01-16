package ru.wallpost.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.wallpost.DTO.LoginDTO;
import ru.wallpost.DTO.RegisterDTO;
import ru.wallpost.exception.UserAlreadyExistsException;
import ru.wallpost.service.AuthService;
import ru.wallpost.util.AuthResponse;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> signIn(@RequestBody @Valid LoginDTO loginDTO, BindingResult bindingResult) throws BadCredentialsException {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("Неверный формат введенных данных");
        }
        AuthResponse authResponse = new AuthResponse("Пользователь успешно авторизован",
                authService.SignIn(loginDTO), LocalDateTime.now());
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<AuthResponse> signUp(@RequestBody @Valid RegisterDTO registerDTO, BindingResult bindingResult) throws UserAlreadyExistsException {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("Неверный формат введенных данных");
        }
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            throw new IllegalArgumentException("Пароли не совпадают");
        }
        AuthResponse authResponse = new AuthResponse("Пользователь успешно зарегистрирован",
                authService.SignUp(registerDTO), LocalDateTime.now());
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

}
