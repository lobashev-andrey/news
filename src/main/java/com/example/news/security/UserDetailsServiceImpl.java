package com.example.news.security;

import com.example.news.service.DatabaseUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final DatabaseUserService service;
    @Override
    public UserDetails loadUserByUsername(String username) {
        return new AppUserPrincipal(service.findByName(username));
    }
}
