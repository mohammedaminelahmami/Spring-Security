package com.example.springsecurityoauth2oidc;

import com.example.springsecurityoauth2oidc.Security.RsaKeysConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeysConfig.class)
public class SpringSecurityOauth2OidcApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityOauth2OidcApplication.class, args);
    }

}
