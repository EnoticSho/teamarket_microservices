package com.example.teamarket.auth.services;

import com.example.teamarket.auth.entities.User;
import com.example.teamarket.auth.exceptions.ResourceNotFoundException;
import com.example.teamarket.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> ResourceNotFoundException.of(email, User.class));
    }

    public UserDetailsService userDetailsService() {
        return this::findByEmail;
    }

    public User getCurrentUser() {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        return findByEmail(email);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
