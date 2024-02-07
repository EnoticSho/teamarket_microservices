package com.example.teamarket.auth.services.impl;

import com.example.teamarket.auth.dto.request.SignInRequest;
import com.example.teamarket.auth.dto.request.SignUpRequest;
import com.example.teamarket.auth.dto.response.JwtAuthenticationResponse;
import com.example.teamarket.auth.entities.Role;
import com.example.teamarket.auth.entities.User;
import com.example.teamarket.auth.integrations.CartServiceIntegration;
import com.example.teamarket.auth.mapper.UserMapper;
import com.example.teamarket.auth.services.AuthenticationService;
import com.example.teamarket.auth.services.JwtService;
import com.example.teamarket.auth.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    private final CartServiceIntegration cartServiceIntegration;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtService jwtService;
    private final UserMapper userMapper;


    @Transactional
    public JwtAuthenticationResponse signUp(@Valid SignUpRequest request, String cartId) {
        User user = userMapper.toUser(request);
        String encodedPassword = passwordEncoder.encode(request.password());
        user.setPassword(encodedPassword);
        user.setRole(Role.ROLE_USER);

        userService.save(user);

        cartServiceIntegration.mergeCarts(cartId, user.getEmail())
                .subscribe();

        String accessJwt = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return new JwtAuthenticationResponse(accessJwt, refreshToken);
    }


    public JwtAuthenticationResponse signIn(@Valid SignInRequest request, String cartId) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
        ));

        UserDetails userDetails = userService
                .userDetailsService()
                .loadUserByUsername(request.email());

        cartServiceIntegration.mergeCarts(cartId, request.email())
                .subscribe();

        String accessJwt = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        return new JwtAuthenticationResponse(accessJwt, refreshToken);
    }


    public JwtAuthenticationResponse refreshAccessToken(String refreshToken) {
        String emailFromToken = jwtService.getEmailFromToken(refreshToken);
        if (jwtService.isInvalid(refreshToken) || emailFromToken == null) {
            throw new RuntimeException();
        }

        UserDetails userDetails = userService.userDetailsService()
                .loadUserByUsername(emailFromToken);
        String newAccessToken = jwtService.generateToken(userDetails);
        return new JwtAuthenticationResponse(newAccessToken, refreshToken);
    }
}
