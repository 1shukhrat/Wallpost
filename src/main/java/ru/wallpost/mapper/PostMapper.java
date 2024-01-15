package ru.wallpost.mapper;


import ru.wallpost.DTO.PostDTO;
import ru.wallpost.entity.Post;

import java.util.List;

public class PostMapper {

    public static PostDTO toDTO(Post post) {
        return PostDTO.builder()
                .id(post.getId())
                .user(UserMapper.toDTO(post.getOwner()))
                .text(post.getText())
                .images(ImageMapper.toDTO(List.copyOf(post.getImages())))
                .countLikes(post.getLikes().size())
                .countCommentaries(post.getComments().size())
                .date(post.getDate())
                .build();
    }

    public static List<PostDTO> toDTO(List<Post> posts) {
        return posts.stream().map(PostMapper::toDTO).toList();
    }
}