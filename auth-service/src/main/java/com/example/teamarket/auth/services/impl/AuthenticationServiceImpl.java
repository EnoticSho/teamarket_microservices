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
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Transactional
    public JwtAuthenticationResponse signUp(SignUpRequest request, String cartId) {
        User user = userMapper.toUser(request);
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(Role.ROLE_USER);

        userService.save(user);

        String AccessJwt = jwtService.generateToken(user, cartId);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new JwtAuthenticationResponse(AccessJwt, refreshToken);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request, String cartId) {
        System.out.println(123);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        System.out.println(1);
        UserDetails userDetails = userService
                .userDetailsService()
                .loadUserByUsername(request.getEmail());
        System.out.println(2);
        String AccessJwt = jwtService.generateToken(userDetails, cartId);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        return new JwtAuthenticationResponse(AccessJwt, refreshToken);
    }

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
