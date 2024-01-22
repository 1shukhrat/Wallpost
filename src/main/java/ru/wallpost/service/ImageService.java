package ru.wallpost.service;


import ru.wallpost.entity.Image;

import java.io.IOException;

public interface ImageService {

    byte[] get(String link) throws IOException;
    Image save(Image image);
    void remove(long id) throws IOException;
}
