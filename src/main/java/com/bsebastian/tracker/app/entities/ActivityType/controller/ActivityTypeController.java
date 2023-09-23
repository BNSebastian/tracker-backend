package com.bsebastian.tracker.app.entities.ActivityType.controller;

import com.bsebastian.tracker.app.entities.ActivityType.model.dto.ActivityTypeCreateDto;
import com.bsebastian.tracker.app.entities.ActivityType.model.dto.ActivityTypeReadDto;

import com.bsebastian.tracker.app.entities.ActivityType.service.ActivityTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activityType")
public class ActivityTypeController {
    private final ActivityTypeService service;


    public ActivityTypeController(ActivityTypeService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<ActivityTypeReadDto>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityTypeReadDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getById(id),HttpStatus.OK);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ActivityTypeReadDto> create(@RequestBody ActivityTypeCreateDto inputDto) {
        return new ResponseEntity<>(service.create(inputDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityTypeReadDto> update(@RequestBody ActivityTypeReadDto inputDto, @PathVariable("id") Long id) {
        return new ResponseEntity<>(service.update(inputDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>("--- entry with id " + id + " was successfully deleted", HttpStatus.OK);
    }
}
