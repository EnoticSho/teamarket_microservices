package com.example.teamarket.cart.config;

import com.example.teamarket.cart.filter.HeadersFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * Configuration class for setting up security and authentication in the cart service.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures the security filter chain for the cart service.
     *
     * @param http The HttpSecurity object to configure.
     * @return A SecurityFilterChain configured with security rules and filters.
     * @throws Exception if there is an error while configuring security.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/v1/api/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/swagger-resources/*", "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .addFilterBefore(new HeadersFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * Retrieves the AuthenticationManager bean from the AuthenticationConfiguration.
     *
     * @param configuration The AuthenticationConfiguration to retrieve the AuthenticationManager from.
     * @return An AuthenticationManager for authentication purposes.
     * @throws Exception if there is an error while retrieving the AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws
            Exception {
        return configuration.getAuthenticationManager();
    }
}
