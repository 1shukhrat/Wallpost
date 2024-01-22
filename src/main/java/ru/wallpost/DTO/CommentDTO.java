package ru.wallpost.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class CommentDTO {
    private long id;
    private UserDTO owner;
    private LocalDateTime date;
    private String text;
}
