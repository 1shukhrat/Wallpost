package ru.wallpost.service;

import jakarta.transaction.Transactional;
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

    @Transactional
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

    @Transactional
    @Override
    public void remove() throws IllegalStateException{
        User user = userRepository.findByLogin(AuthService.getAuthenticated().getUsername()).get();
        user.getSubscriptions().forEach(sub -> sub.getSubscribers().remove(user));
        user.getSubscribers().forEach(sub -> sub.getSubscriptions().remove(user));
        userRepository.delete(user);
    }
}
