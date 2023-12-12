package com.bsebastian.tracker.logic.controller;

import com.bsebastian.tracker.logic.service.ActivityService;
import com.bsebastian.tracker.security.model.UserEntity;
import com.bsebastian.tracker.security.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/time")
public class TimeController {
    private final ActivityService activityService;
    private final UserService userService;

    public TimeController(ActivityService activityService, UserService userService) {
        this.activityService = activityService;
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<HashMap<String, Object>>> getTime(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(activityService.getTime(userId), HttpStatus.OK);
    }

    @GetMapping("/ADMIN/{userId}")
    public ResponseEntity<List<HashMap<String, Object>>> getTimeForAll(@PathVariable("userId") Long userId) {
        if (!userService.checkIfAdmin(userId)) {
            System.out.println("user is admin: " + userService.checkIfAdmin(userId));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<UserEntity> users = userService.getAll().orElseThrow(); // Fetch all users

        List<HashMap<String, Object>> output = new ArrayList<>();
        for (var user: users) {
            String userName = user.getEmail();
            Long currentUserId = user.getId();
            List<HashMap<String, Object>> userTimeData = activityService.getTime(currentUserId);

            HashMap<String, Object> map = new HashMap<>();
            map.put("user", userName);
            map.put("details", userTimeData);
            output.add(map);
        }
        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
