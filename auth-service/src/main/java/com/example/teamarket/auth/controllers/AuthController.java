package com.example.teamarket.auth.controllers;

import com.example.teamarket.auth.dto.request.SignInRequest;
import com.example.teamarket.auth.dto.request.SignUpRequest;
import com.example.teamarket.auth.dto.response.JwtAuthenticationResponse;
import com.example.teamarket.auth.exceptions.TokenRefreshException;
import com.example.teamarket.auth.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * The controller for managing user authentication and registration processes.
 * Provides APIs for registering new users, authenticating, and refreshing access tokens.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Register user and issue token", description = "Registers a new user and issues a JWT token for subsequent authentication.")
    public JwtAuthenticationResponse signUp(@RequestBody SignUpRequest request,
                                            @RequestHeader("cart_id") String cartId,
                                            HttpServletResponse response) {
        JwtAuthenticationResponse jwtAuthenticationResponse = authenticationService.signUp(request, cartId);

        setRefreshTokenToCookie(response, jwtAuthenticationResponse.refreshToken());

        return jwtAuthenticationResponse;
    }

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Authenticate user and issue token", description = "Authenticates a user based on credentials and issues a JWT token for access.")
    public JwtAuthenticationResponse signIn(@RequestBody SignInRequest request,
                                            @RequestHeader("cart_id") String cartId,
                                            HttpServletResponse response) {
        JwtAuthenticationResponse jwtAuthenticationResponse = authenticationService.signIn(request, cartId);

        setRefreshTokenToCookie(response, jwtAuthenticationResponse.refreshToken());

        return jwtAuthenticationResponse;
    }

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Refresh access token", description = "Refreshes the access token using the provided refresh token.")
    public JwtAuthenticationResponse refreshAccessToken(HttpServletRequest request,
                                                        HttpServletResponse response) {
        String refreshToken = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("refreshToken")) {
                    refreshToken = cookie.getValue();
                    break;
                }
            }
        }

        if (refreshToken == null) {
            throw new TokenRefreshException("Refresh token is missing");
        }

        setRefreshTokenToCookie(response, refreshToken);

        return authenticationService.refreshAccessToken(refreshToken);
    }

    private void setRefreshTokenToCookie(HttpServletResponse response, String refreshToken) {
        Cookie refreshCookie = new Cookie("refreshToken", refreshToken);
        refreshCookie.setHttpOnly(true);
        refreshCookie.setSecure(false); // В production следует установить в true
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(refreshCookie);
    }
}
