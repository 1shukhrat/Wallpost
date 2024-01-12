package ru.wallpost.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.wallpost.DTO.RegisterDTO;
import ru.wallpost.entity.User;
import ru.wallpost.exception.UserAlreadyExistsException;
import ru.wallpost.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User add(RegisterDTO registerDTO) {
        if (userRepository.findByLogin(registerDTO.getLogin()).isPresent()) {
            throw new UserAlreadyExistsException(registerDTO.getLogin());
        } else {
            User user = User.builder()
                    .login(registerDTO.getLogin())
                    .password(passwordEncoder.encode(registerDTO.getPassword()))
                    .name(registerDTO.getName())
                    .build();
            userRepository.save(user);
            return user;
        }
    }

    @Override
    public boolean remove(long id) {
        return false;
    }
}
