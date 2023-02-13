package com.example.springsecurityjwt.Util;

import com.example.springsecurityjwt.Entity.User;
import com.example.springsecurityjwt.Enum.Role;
import com.example.springsecurityjwt.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FirstTimeInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {

        if(userRepository.findByUsername("amine") == null) {
            String username = "amine";
            String password = "amine0029+0";
            Role role = Role.ROLE_USER;

            userRepository.save(User.builder().username(username).password(passwordEncoder.encode(password)).role(role).build());
        }else {
            System.out.println("User already exists");
        }

        System.out.println("Starting ...");
    }
}
