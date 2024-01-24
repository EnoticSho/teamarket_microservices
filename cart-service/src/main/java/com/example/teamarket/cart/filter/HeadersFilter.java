package com.example.teamarket.cart.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A custom filter responsible for processing headers in incoming HTTP requests and setting up the Spring Security context
 * based on the provided headers.
 */
@Component
public class HeadersFilter extends OncePerRequestFilter {

    /**
     * Intercept incoming HTTP requests, extract relevant headers, and set up the Spring Security context.
     *
     * @param request     The incoming HTTP request.
     * @param response    The HTTP response.
     * @param filterChain The filter chain for handling the request.
     * @throws ServletException If a servlet exception occurs.
     * @throws IOException      If an I/O exception occurs.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Extract the 'email' and 'roles' headers from the request
        String login = request.getHeader("email");
        String roles = request.getHeader("roles");

        if (login != null && roles != null) {
            // Construct a list of GrantedAuthority objects based on the 'roles' header
            List<GrantedAuthority> authorities = Arrays.stream(roles.split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            // Create an Authentication object with the extracted information
            Authentication authentication = new UsernamePasswordAuthenticationToken(login, null, authorities);

            // Set the Authentication object in the Spring Security context
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Allow the request to continue to the next filter in the filter chain
        filterChain.doFilter(request, response);
    }
}
