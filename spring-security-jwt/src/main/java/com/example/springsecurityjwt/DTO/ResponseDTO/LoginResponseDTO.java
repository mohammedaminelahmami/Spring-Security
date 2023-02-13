package com.example.springsecurityjwt.DTO.ResponseDTO;

import lombok.*;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDTO {
    private ResponseEntity<Map<String, Object>> token;
}
