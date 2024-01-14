package ru.wallpost.DTO;

import lombok.Builder;

@Builder
public class UserDTO {
    private long id;
    private String name;
    private String avatar;
}
