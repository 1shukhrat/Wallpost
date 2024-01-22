package ru.wallpost.mapper;

import ru.wallpost.DTO.CommentDTO;
import ru.wallpost.entity.Comment;

import java.util.List;

public class CommentMapper {

    public static CommentDTO toDTO(Comment comment) {
        return CommentDTO.builder()
                .id(comment.getId())
                .text(comment.getText())
                .date(comment.getDate())
                .owner(UserMapper.toDTO(comment.getOwner()))
                .build();
    }

    public static List<CommentDTO> toDTO(List<Comment> comments) {
        return comments.stream().map(CommentMapper::toDTO).toList();
    }
}
