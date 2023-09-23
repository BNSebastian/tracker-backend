package com.bsebastian.tracker.app.entities.Activity.controller;

import com.bsebastian.tracker.app.entities.Activity.model.dto.ActivityCreateDto;
import com.bsebastian.tracker.app.entities.Activity.model.dto.ActivityReadDto;
import com.bsebastian.tracker.app.entities.Activity.service.ActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {
    private final ActivityService service;

    public ActivityController(ActivityService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<ActivityReadDto>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityReadDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getById(id),HttpStatus.OK);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ActivityReadDto> create(@RequestBody ActivityCreateDto inputDto) {
        return new ResponseEntity<>(service.create(inputDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityReadDto> update(@RequestBody ActivityReadDto inputDto, @PathVariable("id") Long id) {
        return new ResponseEntity<>(service.update(inputDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>("--- entry with id " + id + " was successfully deleted", HttpStatus.OK);
    }

    @PostMapping("/{activityId}/{activityTypeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ActivityReadDto> addActivityType(
            @PathVariable("activityId") Long activityId,
            @PathVariable("activityTypeId") Long activityTypeId
    ) {
        System.out.println("adding activity type with id: " + activityTypeId + " to activity with id: " + activityId);
        return new ResponseEntity<>(service.addActivityType(activityId, activityTypeId),
                                    HttpStatus.CREATED);
    }
}
