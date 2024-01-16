package com.example.teamarket.auth.services;

import com.example.teamarket.auth.dto.request.SignInRequest;
import com.example.teamarket.auth.dto.request.SignUpRequest;
import com.example.teamarket.auth.dto.response.JwtAuthenticationResponse;
import com.example.teamarket.auth.entities.User;
import com.example.teamarket.auth.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Transactional
    public JwtAuthenticationResponse signUp(SignUpRequest request, String cartId) {
        User user = userMapper.toUser(request);
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);
        user.setRoles(List.of(roleService.getRoleByName("ROLE_USER")));

        userService.save(user);

        String AccessJwt = jwtService.generateToken(user, cartId);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new JwtAuthenticationResponse(AccessJwt, refreshToken);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request, String cartId) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        UserDetails userDetails = userService
                .userDetailsService()
                .loadUserByUsername(request.getEmail());

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
