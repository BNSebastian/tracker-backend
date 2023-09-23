package com.bsebastian.tracker.security.authentication;

import com.bsebastian.tracker.security.configuration.JwtService;
import com.bsebastian.tracker.security.user.Role;
import com.bsebastian.tracker.security.user.UserEntity;
import com.bsebastian.tracker.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException();
        }

        var user = UserEntity.builder()
                             .firstname(request.getFirstname())
                             .lastname(request.getLastname())
                             .email(request.getEmail())
                             .password(passwordEncoder.encode(request.getPassword()))
                             .role(Role.USER)
                             .build();

        var savedUser = repository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                                     .token(jwtToken)
                                     .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = repository.findByEmail(request.getEmail())
                             .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                                     .token(jwtToken)
                                     .build();
    }
}