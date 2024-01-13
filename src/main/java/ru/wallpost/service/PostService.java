package ru.wallpost.service;

import ru.wallpost.DTO.AddPostDTO;
import ru.wallpost.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> getAll(int page);
    Post post(AddPostDTO addPostDTO);
    Post edit();
    void remove(long id);



}
