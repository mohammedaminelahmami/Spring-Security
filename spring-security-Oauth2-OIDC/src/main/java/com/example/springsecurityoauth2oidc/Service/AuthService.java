package com.example.springsecurityoauth2oidc.Service;

import com.example.springsecurityoauth2oidc.DTO.RequestDTO.LoginRequestDTO;
import com.example.springsecurityoauth2oidc.DTO.ResponseDTO.LoginResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthService {
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    public LoginResponseDTO authenticate(LoginRequestDTO loginRequestDTO) {
        String subject = null;
        String scope = null;
        if(loginRequestDTO.getGrantType().equals("password")) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
            );
            subject = authentication.getName();
            scope = authentication.getAuthorities().stream().map(auth->auth.getAuthority()).collect(Collectors.joining(" "));
        }else if(loginRequestDTO.getGrantType().equals("refreshToken")) {
            Jwt decodeJWT = jwtDecoder.decode(loginRequestDTO.getRefreshToken());
            subject = decodeJWT.getSubject();
            UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            scope = authorities.stream().map(auth->auth.getAuthority()).collect(Collectors.joining(" "));
        }

        Instant instant = Instant.now();
        Map<String, Object> res = new HashMap<>();

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(instant)
                .expiresAt(instant.plus(loginRequestDTO.isWithRefreshToken() ? 5 : 30, ChronoUnit.MINUTES))
                .issuer("security")
                .claim("scope", scope)
                .build();

        String jwtAccessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        res.put("accessToken", jwtAccessToken);

        if(loginRequestDTO.isWithRefreshToken()) {
            JwtClaimsSet jwtClaimsSetRefresh = JwtClaimsSet.builder()
                    .subject(subject)
                    .issuedAt(instant)
                    .expiresAt(instant.plus(30, ChronoUnit.MINUTES))
                    .issuer("security")
                    .build();

            String jwtAccessRefreshToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSetRefresh)).getTokenValue();
            res.put("refreshToken", jwtAccessRefreshToken);
        }

        return LoginResponseDTO.builder().response(ResponseEntity.ok(res)).build();
    }
}
