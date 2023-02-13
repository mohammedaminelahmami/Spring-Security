package com.example.springsecurityjwt.Entity;

import com.example.springsecurityjwt.Enum.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "User", catalog = "spring-security-jjwt")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private Role role;
}