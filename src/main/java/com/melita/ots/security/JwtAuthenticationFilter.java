package com.melita.ots.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Extract the token from the Authorization header
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        // Check if the Authorization header is present and starts with "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extract the JWT from the header
        jwt = authHeader.substring(7); // Remove the "Bearer " prefix
        username = jwtService.extractUsername(jwt); // Extract the username from the token

        // Check if the username is present and if the authentication context is not already set
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Create a UserDetails object (simplified for the example)
            UserDetails userDetails = User.builder()
                    .username(username)
                    .password("") // No password required in this case
                    .authorities("USER") // Simple role, can be extended as needed
                    .build();

            // Validate the token
            if (jwtService.isTokenValid(jwt, userDetails)) {
                // Create an authenticated object using UsernamePasswordAuthenticationToken
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null, // No credentials required (just the token)
                        userDetails.getAuthorities()
                );

                // Set request details into the authentication object
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Store the authentication object in the SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}