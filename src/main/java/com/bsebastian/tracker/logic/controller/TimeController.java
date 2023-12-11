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
    public ResponseEntity<HashMap<String, List<HashMap<String, Object>>>> getTimeForAll(@PathVariable("userId") Long userId) {
        if (!userService.checkIfAdmin(userId)) {
            System.out.println("user is admin: " + userService.checkIfAdmin(userId));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<UserEntity> users = userService.getAll().orElseThrow(); // Fetch all users

        HashMap<String, List<HashMap<String, Object>>> result = new HashMap<>();

        for (UserEntity user : users) {
            Long currentUserId = user.getId();
            List<HashMap<String, Object>> userTimeData = activityService.getTime(currentUserId);

            String userName = user.getFirstname() + " " + user.getLastname();

            // Add user time data to the result map with the user's name as the key
            result.put(userName, userTimeData);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
