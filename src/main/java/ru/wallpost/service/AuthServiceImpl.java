package ru.wallpost.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.wallpost.DTO.LoginDTO;
import ru.wallpost.DTO.RegisterDTO;
import ru.wallpost.security.JwtUtils;
import ru.wallpost.exception.UserAlreadyExistsException;

@Service
public class AuthServiceImpl implements AuthService{

    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthServiceImpl(JwtUtils jwtUtils, UserService userService, AuthenticationManager authenticationManager) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }


    @Transactional
    @Override
    public String SignUp(RegisterDTO registerDTO) throws UserAlreadyExistsException {
        UserDetails userDetails = userService.add(registerDTO);
        return jwtUtils.generateToken(userDetails);
    }

    @Override
    public String SignIn(LoginDTO loginDTO) throws BadCredentialsException {
        Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginDTO.getLogin(), loginDTO.getPassword()));
        return jwtUtils.generateToken((UserDetails) authentication.getPrincipal());

    }

}
