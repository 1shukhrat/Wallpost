package ru.wallpost.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterDTO {

    @NotBlank
    private String login;

    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@()$!%*#?&])[A-Za-z\\d@()$!%*#?&]{8,20}$")
    private String password;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@()$!%*#?&])[A-Za-z\\d@()$!%*#?&]{8,20}$")
    private String confirmPassword;
}
