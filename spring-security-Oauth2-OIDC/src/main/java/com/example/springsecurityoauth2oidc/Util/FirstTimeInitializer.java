package com.example.springsecurityoauth2oidc.Util;


import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FirstTimeInitializer implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Starting ...");
    }
}
