package ru.wallpost.mapper;

import ru.wallpost.DTO.UserDTO;
import ru.wallpost.entity.User;

import java.util.List;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .avatar(user.getAvatarLink())
                .build();
    }

    public static List<UserDTO> toDTO(List<User> users) {
        return users.stream().map(UserMapper::toDTO).toList();
    }
}
