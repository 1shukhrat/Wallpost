package ru.wallpost.service;

import ru.wallpost.DTO.RegisterDTO;
import ru.wallpost.entity.User;

public interface UserService {

    User add(RegisterDTO registerDTO);
    boolean remove(long id);
}
