package ru.wallpost.mapper;

import ru.wallpost.DTO.ImageDTO;
import ru.wallpost.entity.Image;

import java.util.List;

public class ImageMapper {

    public static ImageDTO toDTO(Image image) {
        return ImageDTO.builder()
                .id(image.getId())
                .link(image.getLink())
                .build();
    }

    public static List<ImageDTO> toDTO(List<Image> images) {
        return images.stream().map(ImageMapper::toDTO).toList();
    }
}
