package com.example.springsecurityoauth2oidc.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @GetMapping
    public Map<String, Object> getProduct(){
        return Map.of("product1", "iPhone 14");
    }
}
