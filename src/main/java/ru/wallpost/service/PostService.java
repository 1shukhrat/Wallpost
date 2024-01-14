package ru.wallpost.service;

import ru.wallpost.DTO.AddPostDTO;
import ru.wallpost.entity.Post;

import java.io.IOException;
import java.util.List;

public interface PostService {

    List<Post> getAll(int page) throws IllegalStateException;
    List<Post> getAllByUser(long userId, int page) throws IllegalStateException;

    List<Post> getAllByAuthenticated(int page) throws IllegalStateException;

    Post post(AddPostDTO addPostDTO) throws IOException, IllegalStateException;
    void remove(long id) throws IOException, IllegalStateException;


}
