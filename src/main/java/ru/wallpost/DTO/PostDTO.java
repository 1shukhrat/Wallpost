package ru.wallpost.DTO;


import lombok.Builder;

import java.util.List;

@Builder
public class PostDTO {
    private long id;
    private UserDTO user;
    private String text;
    private List<ImageDTO> images;
    private int countLikes;
    private int countCommentaries;
}
