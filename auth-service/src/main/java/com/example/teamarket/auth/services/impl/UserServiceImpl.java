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

    /**
     * Finds a user by their email address and returns a DTO containing user information.
     *
     * @param email The email address of the user to find.
     * @return A UserInfoDto containing user information.
     * @throws ResourceNotFoundException if the user with the given email address is not found.
     */
    public UserInfoDto findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toUserInfoDto)
                .orElseThrow(() -> ResourceNotFoundException.of(email, User.class));
    }

    /**
     * Creates a UserDetailsService that retrieves a user by their email address.
     *
     * @return A UserDetailsService for retrieving users by email address.
     */
    public UserDetailsService userDetailsService() {
        return email -> userRepository.findByEmail(email)
                .orElseThrow(() -> ResourceNotFoundException.of(email, User.class));
    }

    /**
     * Saves a user to the repository.
     *
     * @param user The user to save.
     */
    public void save(User user) {
        userRepository.save(user);
    }
}
