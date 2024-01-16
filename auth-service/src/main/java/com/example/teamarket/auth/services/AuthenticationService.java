package com.example.teamarket.auth.services;

import com.example.teamarket.auth.dto.request.SignInRequest;
import com.example.teamarket.auth.dto.response.JwtAuthenticationResponse;
import com.example.teamarket.auth.dto.request.SignUpRequest;
import com.example.teamarket.auth.entities.User;
import com.example.teamarket.auth.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    public JwtAuthenticationResponse signUp(SignUpRequest request) {

        User user = userMapper.toUser(request);

        userService.save(user);

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getEmail());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}
