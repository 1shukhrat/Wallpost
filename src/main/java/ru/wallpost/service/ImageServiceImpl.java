package ru.wallpost.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.wallpost.entity.Image;
import ru.wallpost.repository.ImageRepository;
import ru.wallpost.util.FileUtil;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public byte[] get(String fileName) throws IOException {
        return FileUtil.get(fileName);
    }

    @Transactional
    @Override
    public Image save(MultipartFile file) throws IOException {
        String url = FileUtil.save(file);
        return imageRepository.save(Image.builder().link(url).build());
    }

    @Transactional
    @Override
    public void remove(long id) throws IOException {
        Optional<Image> imageOptional = imageRepository.findById(id);
        if (imageOptional.isPresent()) {
            String fileLink = imageOptional.get().getLink();
            String fileName = fileLink.substring(fileLink.lastIndexOf("/") + 1);
            FileUtil.remove(fileName);
            imageRepository.delete(imageOptional.get());
        }
    }
}
