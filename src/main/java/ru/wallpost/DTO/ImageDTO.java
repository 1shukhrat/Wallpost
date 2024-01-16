package ru.wallpost.DTO;



import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class ImageDTO {
    private long id;
    private String link;
}
