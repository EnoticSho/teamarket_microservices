package com.example.teamarket.auth.controllers;

import com.example.teamarket.auth.dto.request.SignInRequest;
import com.example.teamarket.auth.dto.request.SignUpRequest;
import com.example.teamarket.auth.dto.response.JwtAuthenticationResponse;
import com.example.teamarket.auth.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestHeader("cart_id") String cartId,
                                            @RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request, cartId);
    }

    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestHeader("cart_id") String cartId,
                                            @RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request, cartId);
    }

    @PostMapping("/refresh")
    public JwtAuthenticationResponse refreshAccessToken(@RequestHeader("cart_id") String cartId,
                                                        @RequestParam String refreshToken) {
        return authenticationService.refreshAccessToken(refreshToken, cartId);
    }
}
