package com.bsebastian.tracker.app.components.activity;

import com.bsebastian.tracker.app.components.activity.model.ActivityCreateDto;
import com.bsebastian.tracker.app.components.activity.model.ActivityReadDto;
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

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ActivityReadDto> create(
            @RequestBody ActivityCreateDto activity,
            @PathVariable("id") Long userId
    ) {
        return new ResponseEntity<>(activityService.create(activity, userId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityReadDto> update(@RequestBody ActivityReadDto inputDto, @PathVariable("id") Long id) {
        return new ResponseEntity<>(activityService.update(inputDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        activityService.delete(id);
        return new ResponseEntity<>("--- entry with id " + id + " was successfully deleted", HttpStatus.OK);
    }

    @PostMapping("/{activityId}/{activityTypeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ActivityReadDto> addActivityType(
            @PathVariable("activityId") Long activityId,
            @PathVariable("activityTypeId") Long activityTypeId
    ) {
        System.out.println("adding activity type with id: " + activityTypeId + " to activity with id: " + activityId);
        return new ResponseEntity<>(activityService.addType(activityId, activityTypeId),
                                    HttpStatus.CREATED);
    }
}
