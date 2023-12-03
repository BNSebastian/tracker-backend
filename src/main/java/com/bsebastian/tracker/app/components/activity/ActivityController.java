package com.bsebastian.tracker.app.components.activity;

import com.bsebastian.tracker.app.components.activity.model.ActivityCreateDto;
import com.bsebastian.tracker.app.components.activity.model.ActivityReadDto;
import com.bsebastian.tracker.app.components.activity.model.ActivityUpdateDto;
import com.bsebastian.tracker.app.components.activity.persistence.ActivityService;
import com.bsebastian.tracker.security.model.UserEntity;
import com.bsebastian.tracker.security.persistence.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {
    private final ActivityService activityService;
    private final UserService userService;

    public ActivityController(ActivityService service, UserService userService) {
        this.activityService = service;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<ActivityReadDto>> getAll() {
        return new ResponseEntity<>(activityService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityReadDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(activityService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/{userId}/{typeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ActivityReadDto> create(
            @RequestBody ActivityCreateDto activity,
            @PathVariable("userId") Long userId,
            @PathVariable("typeId") Long typeId
    ) {
        return new ResponseEntity<>(activityService.create(activity, userId, typeId), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<ActivityReadDto> update(@RequestBody ActivityUpdateDto activity) {
        return new ResponseEntity<>(activityService.update(activity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        activityService.delete(id);
        return new ResponseEntity<>("--- entry with id " + id + " was successfully deleted", HttpStatus.OK);
    }

    @GetMapping("/time/{userId}")
    public ResponseEntity<List<HashMap<String, Object>>> getTime(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(activityService.getTime(userId), HttpStatus.OK);
    }

    @GetMapping("/ADMIN/time/{userId}")
    public ResponseEntity<List<List<HashMap<String, Object>>>> getTimeForAll(@PathVariable("userId") Long userId) {
        if (!userService.checkIfAdmin(userId)) {
            System.out.println("user is admin: " + userService.checkIfAdmin(userId));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<UserEntity> users = userService.getAll().orElseThrow(); // Fetch all users

        List<List<HashMap<String, Object>>> resultList = new ArrayList<>();

        for (UserEntity user : users) {
            Long currentUserId = user.getId();
            List<HashMap<String, Object>> userTimeData = activityService.getTime(currentUserId);

            // Add user time data to the result list
            resultList.add(userTimeData);
        }

        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }
}
