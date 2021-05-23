package com.subadvisor.config;


import com.subadvisor.auth.JwtRequestFilter;
import com.subadvisor.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;


/**
 * This class is required to customize the Spring Security layer
 *
 * @author Matti Henning
 */

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    Logger logger = LoggerFactory.getLogger("Logger");

    @Autowired
    private UserService userService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
        // registration
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.headers().frameOptions().disable().and().cors().and().csrf().disable();

        // Set session management to stateless
        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // Set unauthorized requests exception handler
        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            logger.error("Unauthorized request - {}", ex.getMessage());
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
                        }
                )
                .and();


        http
                .authorizeRequests()
                // public endpoints
                .antMatchers(HttpMethod.GET,"/authenticate/**").permitAll()
                .antMatchers(HttpMethod.POST,"/authenticate/**").permitAll()
                .antMatchers(HttpMethod.GET, "/venues/**").permitAll()//.hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/events/**").permitAll()//.hasRole("ADMIN")

                .antMatchers("/").permitAll()
                .anyRequest().authenticated();


        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        /*
        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.headers().frameOptions().disable().and().cors().and().csrf().disable();
            httpSecurity.addFilterBefore(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        }

         */

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new WebConfig().bCryptPasswordEncoder();
    //    return NoOpPasswordEncoder.getInstance();
    }
}
