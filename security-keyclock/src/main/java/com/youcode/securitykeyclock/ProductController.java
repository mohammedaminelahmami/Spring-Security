package com.youcode.securitykeyclock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @GetMapping
    public Map<String, Object> getOneProduct() {
        return Map.of("product name", "iPhone 14");
    }
}
