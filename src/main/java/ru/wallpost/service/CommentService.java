package ru.wallpost.service;

import ru.wallpost.DTO.PostCommentDTO;
import ru.wallpost.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAll(long postId, int page);
    Comment add(PostCommentDTO postCommentDTO) throws IllegalArgumentException;
    void remove(long id) throws IllegalArgumentException;
}
