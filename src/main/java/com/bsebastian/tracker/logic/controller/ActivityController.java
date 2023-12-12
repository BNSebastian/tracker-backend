package com.bsebastian.tracker.logic.controller;

import com.bsebastian.tracker.logic.model.dto.ActivityCreateDto;
import com.bsebastian.tracker.logic.model.dto.ActivityReadDto;
import com.bsebastian.tracker.logic.model.dto.ActivityUpdateDto;
import com.bsebastian.tracker.logic.service.ActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {
    private final ActivityService activityService;

    public ActivityController(ActivityService service) {
        this.activityService = service;
    }

    @GetMapping("")
    public ResponseEntity<List<ActivityReadDto>> getAll() {
        return new ResponseEntity<>(activityService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ActivityReadDto>> getAllByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(activityService.getAllByUserId(userId), HttpStatus.OK);
    }


    @GetMapping("getById/{id}")
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
}
