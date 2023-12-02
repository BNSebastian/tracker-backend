package com.bsebastian.tracker.app.components.activity;

import com.bsebastian.tracker.app.components.activity.model.ActivityCreateDto;
import com.bsebastian.tracker.app.components.activity.model.ActivityReadDto;
import com.bsebastian.tracker.app.components.activity.model.ActivityUpdateDto;
import com.bsebastian.tracker.app.components.activity.persistence.ActivityService;
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
}
