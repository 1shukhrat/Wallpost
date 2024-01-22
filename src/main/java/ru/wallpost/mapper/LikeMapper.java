package ru.wallpost.mapper;

import ru.wallpost.DTO.LikeDTO;
import ru.wallpost.entity.Like;

import java.util.List;

public class LikeMapper {

    public static LikeDTO toDTO(Like like) {
        return LikeDTO.builder()
                .id(like.getId())
                .owner(UserMapper.toDTO(like.getOwner()))
                .build();
    }

    public static List<LikeDTO> toDTO(List<Like> likes) {
        return likes.stream().map(LikeMapper::toDTO).toList();
    }
}
