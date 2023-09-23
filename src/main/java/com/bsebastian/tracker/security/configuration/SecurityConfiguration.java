package com.bsebastian.tracker.security.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // disable CSRF
        http.csrf(csrf -> csrf.disable());

        // authorize requests
        http.authorizeHttpRequests(auth -> {
            //auth.requestMatchers(AntPathRequestMatcher.antMatcher("/api/pokemon")).hasRole("ADMIN");
            auth.requestMatchers(AntPathRequestMatcher.antMatcher("/api/auth/**")).permitAll();
            auth.requestMatchers(AntPathRequestMatcher.antMatcher("/api/**")).permitAll();
            //auth.requestMatchers(AntPathRequestMatcher.antMatcher("/api/pokemon")).authenticated();
            //auth.anyRequest().authenticated();
            //auth.anyRequest().permitAll();
        });

        // don't persist log in
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authenticationProvider(authenticationProvider);
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}