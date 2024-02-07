package com.example.teamarket.auth.services;

import com.example.teamarket.auth.dto.request.SignInRequest;
import com.example.teamarket.auth.dto.request.SignUpRequest;
import com.example.teamarket.auth.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {

    JwtAuthenticationResponse signUp(SignUpRequest request, String cartId);
    JwtAuthenticationResponse signIn(SignInRequest request, String cartId);
    JwtAuthenticationResponse refreshAccessToken(String refreshToken);
}
