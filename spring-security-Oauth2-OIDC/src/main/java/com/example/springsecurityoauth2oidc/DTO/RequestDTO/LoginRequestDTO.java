package com.example.springsecurityoauth2oidc.DTO.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginRequestDTO {
    private String username;
    private String password;
    private String grantType;
    private boolean withRefreshToken;
    private String refreshToken;
}