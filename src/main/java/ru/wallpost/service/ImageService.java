package ru.wallpost.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    String save(MultipartFile file) throws IOException;
    void remove(long id);
}
