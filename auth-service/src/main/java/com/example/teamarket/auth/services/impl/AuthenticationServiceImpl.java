package com.example.teamarket.auth.services.impl;

import com.example.teamarket.auth.dto.request.SignInRequest;
import com.example.teamarket.auth.dto.request.SignUpRequest;
import com.example.teamarket.auth.dto.response.JwtAuthenticationResponse;
import com.example.teamarket.auth.entities.Role;
import com.example.teamarket.auth.entities.User;
import com.example.teamarket.auth.mapper.UserMapper;
import com.example.teamarket.auth.services.AuthenticationService;
import com.example.teamarket.auth.services.JwtService;
import com.example.teamarket.auth.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of the AuthenticationService interface for user authentication and authorization.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    /**
     * Registers a new user and returns JWT tokens for authentication.
     *
     * @param request The request object containing user registration data.
     * @param cartId  The cart identifier.
     * @return A JwtAuthenticationResponse containing JWT access and refresh tokens.
     */
    @Transactional
    public JwtAuthenticationResponse signUp(@Valid SignUpRequest request, String cartId) {
        User user = userMapper.toUser(request);
        String encodedPassword = passwordEncoder.encode(request.password());
        user.setPassword(encodedPassword);
        user.setRole(Role.ROLE_USER);

        userService.save(user);

        String accessJwt = jwtService.generateToken(user, cartId);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new JwtAuthenticationResponse(accessJwt, refreshToken);
    }

    /**
     * Authenticates a user and returns JWT tokens for access.
     *
     * @param request The request object containing user login credentials.
     * @param cartId  The cart identifier.
     * @return A JwtAuthenticationResponse containing JWT access and refresh tokens.
     */
    public JwtAuthenticationResponse signIn(SignInRequest request, String cartId) {
        UserDetails userDetails = userService
                .userDetailsService()
                .loadUserByUsername(request.email());

        String accessJwt = jwtService.generateToken(userDetails, cartId);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        return new JwtAuthenticationResponse(accessJwt, refreshToken);
    }

    /**
     * Refreshes the user's access token using a refresh token.
     *
     * @param refreshToken The refresh token.
     * @param cartId       The cart identifier.
     * @return A JwtAuthenticationResponse containing a new JWT access token and the same refresh token.
     * @throws RuntimeException if the refresh token is invalid or the associated email is not found.
     */
    public JwtAuthenticationResponse refreshAccessToken(String refreshToken, String cartId) {
        String emailFromToken = jwtService.getEmailFromToken(refreshToken);
        if (jwtService.isInvalid(refreshToken) || emailFromToken == null) {
            throw new RuntimeException();
        }

        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(emailFromToken);

        String newAccessToken = jwtService.generateToken(userDetails, cartId);

        return new JwtAuthenticationResponse(newAccessToken, refreshToken);
    }
}
