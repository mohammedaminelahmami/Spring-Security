package com.example.springsecurityoauth2oidc.Controller;

import com.example.springsecurityoauth2oidc.DTO.RequestDTO.LoginRequestDTO;
import com.example.springsecurityoauth2oidc.DTO.ResponseDTO.LoginResponseDTO;
import com.example.springsecurityoauth2oidc.Service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        return authService.authenticate(loginRequestDTO);
    }
}
