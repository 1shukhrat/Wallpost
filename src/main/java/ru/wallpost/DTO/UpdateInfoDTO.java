package ru.wallpost.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateInfoDTO {

    @NotBlank
    private String login;

    @NotBlank
    private String name;

    private String mood;
}
