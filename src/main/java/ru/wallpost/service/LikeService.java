package ru.wallpost.service;


import ru.wallpost.entity.Like;

import java.util.List;

public interface LikeService {
    List<Like> getAll(long postId, int page);
    Like add(long postId) throws IllegalArgumentException;
    void remove(long id) throws IllegalArgumentException;

}
