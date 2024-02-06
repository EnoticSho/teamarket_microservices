package com.example.teamarket.auth.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String generateToken(UserDetails userDetails);
    String generateRefreshToken(UserDetails userDetails);
    String getEmailFromToken(String token);
    boolean isInvalid(String token);

}
