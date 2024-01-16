package ru.wallpost.service;

import org.springframework.web.multipart.MultipartFile;
import ru.wallpost.entity.Image;

import java.io.IOException;

public interface ImageService {

    byte[] get(String link) throws IOException;
    Image save(MultipartFile file) throws IOException;
    void remove(long id) throws IOException;
}
