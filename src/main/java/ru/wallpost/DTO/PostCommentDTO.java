package ru.wallpost.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PostCommentDTO {
    @NotBlank
    private String text;
    private long postId;
}
