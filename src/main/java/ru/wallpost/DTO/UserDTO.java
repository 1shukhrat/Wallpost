package ru.wallpost.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserDTO {
    private long id;
    private String name;
    private String avatar;
}
