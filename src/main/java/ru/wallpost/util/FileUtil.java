package ru.wallpost.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileUtil {

    private static final String URL_PATH = "http://localhost:8080/images/";

    public static final String DEFAULT_AVATAR = URL_PATH + "default.jpg";
    private static final String UPLOAD_PATH = "images";

    public static String save(MultipartFile file) throws IOException {
        String fileName = generateUniqueFileName(Objects.requireNonNull(file.getOriginalFilename()));
        Path path = Path.of(UPLOAD_PATH, fileName);
        Files.copy(file.getInputStream(), path);
        return URL_PATH + fileName;
    }

    public static void remove(String fileName) throws IOException {
        Path path = Paths.get(UPLOAD_PATH, fileName);
        Files.deleteIfExists(path);
    }

    private static String generateUniqueFileName(String originalFileName) {
        String extension = originalFileName.substring(
                originalFileName.lastIndexOf("."));
        return UUID.randomUUID() + extension;
    }
}
