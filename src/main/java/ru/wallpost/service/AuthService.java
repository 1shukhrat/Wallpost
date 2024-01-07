package ru.wallpost.service;

import ru.wallpost.DTO.LoginDTO;
import ru.wallpost.DTO.RegisterDTO;


public interface AuthService {

    String SignUp(RegisterDTO registerDTODTO);
    String SignIn(LoginDTO loginDTO);
}
