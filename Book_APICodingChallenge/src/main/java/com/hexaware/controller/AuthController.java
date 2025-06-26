package com.hexaware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.hexaware.entity.User;
import com.hexaware.security.BookDetailsServiceImpl;
import com.hexaware.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private BookDetailsServiceImpl userDetailsService;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private AuthenticationManager authManager;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        String encoded = passwordEncoder.encode(user.getPassword());
        userDetailsService.register(user.getUsername(), encoded);
        return "User registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        return jwtUtil.generateToken(user.getUsername());
    }
}
