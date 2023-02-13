package com.example.springsecurityjwt.Service;

import com.example.springsecurityjwt.DTO.RequestDTO.LoginRequestDTO;
import com.example.springsecurityjwt.DTO.ResponseDTO.LoginResponseDTO;
import com.example.springsecurityjwt.Repository.UserRepository;
import com.example.springsecurityjwt.Security.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenUtil tokenUtil;
    private final UserRepository userRepository;

    @Autowired
    public UserService(@Lazy AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, TokenUtil tokenUtil, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.tokenUtil = tokenUtil;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("amine0029",passwordEncoder.encode("test2009") , AuthorityUtils.NO_AUTHORITIES);
    }

    public LoginResponseDTO loginUser (LoginRequestDTO loginRequestDTO) throws RuntimeException {
        com.example.springsecurityjwt.Entity.User user = userRepository.findByUsername(loginRequestDTO.getUsername());

        if(user != null) {
            System.out.println("inside1 ".repeat(20));
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
            );
            System.out.println("inside2 ".repeat(20));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("inside3 ".repeat(20));

            UserDetails userDetails = this.loadUserByUsername(loginRequestDTO.getUsername());
            System.out.println("inside4 ".repeat(20));

            String token = tokenUtil.generateToken(userDetails);
            System.out.println("inside5 ".repeat(20));

            Map<String, Object> map = new HashMap<>();
            map.put("access token : ", token);
            System.out.println("inside6 ".repeat(20));
            System.out.println(map);
            System.out.println("inside7".repeat(20));

            return LoginResponseDTO.builder().token(ResponseEntity.ok(map)).build();
        }
        System.out.println("not found".repeat(20));
        throw new UsernameNotFoundException("User not found");
    }
}