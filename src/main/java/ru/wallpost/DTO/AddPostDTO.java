package ru.wallpost.DTO;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class AddPostDTO {
    private String text;
    private List<MultipartFile> images;
}
