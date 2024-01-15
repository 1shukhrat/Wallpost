package ru.wallpost.mapper;

import ru.wallpost.DTO.UserDTO;
import ru.wallpost.DTO.UserProfileDTO;
import ru.wallpost.entity.User;

import java.util.List;
import java.util.Set;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .avatar(user.getAvatarLink())
                .build();
    }

    public static UserProfileDTO toProfileDTO(User user) {
        return UserProfileDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .login(user.getLogin())
                .avatar(user.getAvatarLink())
                .mood(user.getMood())
                .subscribersCount(user.getSubscribers().size())
                .subscriptionsCount(user.getSubscriptions().size())
                .build();
    }

    public static List<UserDTO> toDTO(Set<User> users) {
        return users.stream().map(UserMapper::toDTO).toList();
    }
}
