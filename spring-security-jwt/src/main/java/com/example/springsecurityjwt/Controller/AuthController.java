package com.example.springsecurityjwt.Controller;

import com.example.springsecurityjwt.DTO.RequestDTO.LoginRequestDTO;
import com.example.springsecurityjwt.DTO.ResponseDTO.LoginResponseDTO;
import com.example.springsecurityjwt.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final UserService userService;
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return userService.loginUser(loginRequestDTO);
    }
}