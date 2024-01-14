package ru.wallpost.service;

import ru.wallpost.DTO.AddPostDTO;
import ru.wallpost.entity.Post;

import java.io.IOException;
import java.util.List;

public interface PostService {

    List<Post> getAll(int page);
    Post post(AddPostDTO addPostDTO) throws IOException;
    void remove(long id) throws IOException;


}
