package ru.wallpost.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class AddPostDTO {
    private String text;
    private List<MultipartFile> images;
}
