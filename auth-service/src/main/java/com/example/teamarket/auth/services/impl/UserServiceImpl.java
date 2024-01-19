package com.example.teamarket.auth.services.impl;

import com.example.teamarket.auth.dto.response.UserInfoDto;
import com.example.teamarket.auth.entities.User;
import com.example.teamarket.auth.exceptions.ResourceNotFoundException;
import com.example.teamarket.auth.mapper.UserMapper;
import com.example.teamarket.auth.repository.UserRepository;
import com.example.teamarket.auth.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserInfoDto findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toUserInfoDto)
                .orElseThrow(() -> ResourceNotFoundException.of(email, User.class));
    }

    public UserDetailsService userDetailsService() {
        return email -> userRepository.findByEmail(email)
                .orElseThrow(() -> ResourceNotFoundException.of(email, User.class));
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
