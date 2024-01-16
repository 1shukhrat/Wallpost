package ru.wallpost.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.wallpost.service.ImageService;

import java.io.IOException;

@RestController
@RequestMapping("/images")
public class ImageController {
    private final ImageService imageService;
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<byte[]> get(@PathVariable("fileName") String fileName) throws IOException {
        return new ResponseEntity<>(imageService.get(fileName), HttpStatus.OK);
    }

}
