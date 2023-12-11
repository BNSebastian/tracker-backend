package com.bsebastian.tracker.security.controller;

import com.bsebastian.tracker.security.model.AuthenticationRequest;
import com.bsebastian.tracker.security.model.AuthenticationResponse;
import com.bsebastian.tracker.security.service.AuthenticationService;
import com.bsebastian.tracker.security.model.RegisterRequest;
import com.bsebastian.tracker.security.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;
    private final UserServiceImpl userService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/isAdmin")
    public ResponseEntity<Boolean> isAdmin(@RequestBody Map<String, Long> body) {
        Long userId = body.get("userId");
        return ResponseEntity.ok(userService.checkIfAdmin(userId));
    }
}
