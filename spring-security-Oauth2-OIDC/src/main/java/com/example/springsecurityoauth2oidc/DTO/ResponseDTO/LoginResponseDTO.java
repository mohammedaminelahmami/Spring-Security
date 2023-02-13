package com.example.springsecurityoauth2oidc.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class LoginResponseDTO {
    private ResponseEntity<Map<String, Object>> response;
}
