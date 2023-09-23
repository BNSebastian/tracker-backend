package com.bsebastian.tracker.security.authentication;

import com.bsebastian.tracker.app.models.Activity;
import com.bsebastian.tracker.app.models.dtos.ActivityReadDto;
import com.bsebastian.tracker.security.user.UserEntity;
import com.bsebastian.tracker.security.user.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final UserEntityService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/addActivity/{userId}/{activityId}")
    public ResponseEntity<UserEntity> addActivity(
            @RequestBody Activity activity,
            @PathVariable("userId") Long userId,
            @PathVariable("activityId") Long activityId
    ) {
        return ResponseEntity.ok(userService.addActivity(userId, activityId));
    }
}
