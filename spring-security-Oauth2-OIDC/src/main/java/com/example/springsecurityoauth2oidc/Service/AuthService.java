package com.example.springsecurityoauth2oidc.Service;

import com.example.springsecurityoauth2oidc.DTO.ResponseDTO.LoginResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthService {
    private final JwtEncoder jwtEncoder;
    public LoginResponseDTO authenticate(Authentication authentication) {
        Instant instant = Instant.now();
        Map<String, Object> res = new HashMap<>();

        String scope = authentication.getAuthorities().stream().map(auth->auth.getAuthority()).collect(Collectors.joining(" "));

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(authentication.getName())
                .issuedAt(instant)
                .expiresAt(instant.plus(5, ChronoUnit.MINUTES))
                .issuer("security")
                .claim("scope", scope)
                .build();

        String jwtAccessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        res.put("accessToken", jwtAccessToken);
        return LoginResponseDTO.builder().response(ResponseEntity.ok(res)).build();
    }
}
