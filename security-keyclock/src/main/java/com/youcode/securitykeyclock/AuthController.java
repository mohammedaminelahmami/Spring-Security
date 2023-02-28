package com.youcode.securitykeyclock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @GetMapping("/login")
    public String login() {
        return "logged in !";
    }

    @GetMapping("/register")
    public String register() {
        return "register !";
    }
}
