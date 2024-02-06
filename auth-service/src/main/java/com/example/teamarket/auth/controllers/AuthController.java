package com.example.teamarket.auth.controllers;

import com.example.teamarket.auth.dto.request.SignInRequest;
import com.example.teamarket.auth.dto.request.SignUpRequest;
import com.example.teamarket.auth.dto.response.JwtAuthenticationResponse;
import com.example.teamarket.auth.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public JwtAuthenticationResponse signUp(@RequestBody SignUpRequest request) {
        return authenticationService.signUp(request, cartId);
    }


    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Authenticate user and issue token", description = "Authenticates a user based on credentials and issues a JWT token for access.")
    public JwtAuthenticationResponse signIn(@RequestBody SignInRequest request) {
        return authenticationService.signIn(request, cartId);
    }


    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Refresh access token", description = "Refreshes the access token using the provided refresh token.")
    public JwtAuthenticationResponse refreshAccessToken(@RequestParam String refreshToken) {
        return authenticationService.refreshAccessToken(refreshToken, cartId);
    }
}
