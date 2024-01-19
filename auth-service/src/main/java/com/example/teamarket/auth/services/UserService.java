package com.example.teamarket.auth.services;

import com.example.teamarket.auth.dto.response.UserInfoDto;
import com.example.teamarket.auth.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserInfoDto findByEmail(String email);
    UserDetailsService userDetailsService();
    void save(User user);
}
