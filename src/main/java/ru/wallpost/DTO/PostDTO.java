package ru.wallpost.DTO;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
public class PostDTO {
    private long id;
    private UserDTO user;
    private String text;
    private List<ImageDTO> images;
    private int countLikes;
    private int countCommentaries;
    private LocalDateTime date;
}
