package ru.wallpost.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LikeDTO {
    private long id;
    private UserDTO owner;
}
