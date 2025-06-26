package com.hexaware.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookDetailsServiceImpl implements UserDetailsService {

    private final Map<String, String> users = new HashMap<>();

    public void register(String username, String password) {
        users.put(username, password);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = users.get(username);
        if (password == null) throw new UsernameNotFoundException("User not found");
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(password)
                .authorities("USER")
                .build();
    }
}
