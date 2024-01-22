package com.example.teamarket.auth.controllers;

import com.example.teamarket.auth.dto.request.SignInRequest;
import com.example.teamarket.auth.dto.request.SignUpRequest;
import com.example.teamarket.auth.dto.response.JwtAuthenticationResponse;
import com.example.teamarket.auth.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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

    /**
     * Registers a new user and returns a JWT for authentication.
     *
     * @param cartId The cart identifier passed in the request header, representing the user's cart.
     * @param request The request object containing the user's registration data.
     * @return A {@link JwtAuthenticationResponse} object containing the JWT token and authentication information.
     */
    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Register user and issue token", description = "Registers a new user and issues a JWT token for subsequent authentication.")
    public JwtAuthenticationResponse signUp(@RequestHeader("cart_id") String cartId,
                                            @RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request, cartId);
    }

    /**
     * Authenticates a user and returns a JWT for access.
     *
     * @param cartId The cart identifier passed in the request header.
     * @param request The request object containing the user's login credentials.
     * @return A {@link JwtAuthenticationResponse} object containing the JWT token and authentication information.
     */
    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Authenticate user and issue token", description = "Authenticates a user based on credentials and issues a JWT token for access.")
    public JwtAuthenticationResponse signIn(@RequestHeader("cart_id") String cartId,
                                            @RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request, cartId);
    }

    /**
     * Refreshes the user's access token.
     *
     * @param cartId The cart identifier passed in the request header.
     * @param refreshToken The refresh token used to obtain a new access token.
     * @return A refreshed {@link JwtAuthenticationResponse} object containing a new JWT access token.
     */
    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Refresh access token", description = "Refreshes the access token using the provided refresh token.")
    public JwtAuthenticationResponse refreshAccessToken(@RequestHeader("cart_id") String cartId,
                                                        @RequestParam String refreshToken) {
        return authenticationService.refreshAccessToken(refreshToken, cartId);
    }
}
