package ru.wallpost.DTO;


import java.util.Set;

public class PostListDTO {
    private long id;
    private UserDTO user;
    private String text;
    private Set<ImageDTO> images;
    private int countLikes;
    private int countCommentaries;
}
