package com.example.springsecurityoauth2oidc.DTO.RequestDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginRequestDTO {
    @NotBlank(message = "username empty")
    private String username;

    @NotBlank(message = "password empty")
    private String password;
}
