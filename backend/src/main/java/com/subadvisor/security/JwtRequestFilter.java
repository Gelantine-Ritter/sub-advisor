package com.subadvisor.security;

import com.subadvisor.api.user.UserService;
import com.subadvisor.api.venue.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.String.format;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userDetailsService;

    @Autowired
    private VenueService venueDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException, UsernameNotFoundException {


        logger.debug("try to get username from token");


        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);

            logger.debug("try to get username from token");
            username = jwtUtil.extractUsername(jwt);
        }

        // TODO: The whole logic is not clean. to much Boilerplate!

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = null;

            try {
                userDetails = this.venueDetailsService.loadUserByUsername(username);
            } catch (UsernameNotFoundException e) {
                // importan to catch and proceed
            }
            if (userDetails == null) {
                try {
                    userDetails = this.userDetailsService.loadUserByUsername(username);
                }
                catch (UsernameNotFoundException e) {
                    // importan to catch and proceed
                }
            }
            if (userDetails == null) {
                throw new UsernameNotFoundException(
                        format("User with username - %s, not found", username));
            }


            if (jwtUtil.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        // continue with Filterchain
        chain.doFilter(request, response);
    }

}
