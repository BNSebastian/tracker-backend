package com.bsebastian.tracker.app.entities.controllers;

import com.bsebastian.tracker.app.entities.models.dtos.TrackerCreateDto;
import com.bsebastian.tracker.app.entities.models.dtos.TrackerReadDto;
import com.bsebastian.tracker.app.entities.services.TrackerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracker")
public class TrackerController {
    private final TrackerService service;

    public TrackerController(TrackerService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<TrackerReadDto>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrackerReadDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TrackerReadDto> create(
            @RequestBody TrackerCreateDto inputDto,
            @PathVariable("id") Long userId) {
        return new ResponseEntity<>(service.create(inputDto, userId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrackerReadDto> update(
            @RequestBody TrackerReadDto inputDto,
            @PathVariable("id") Long id
    ) {
        return new ResponseEntity<>(service.update(inputDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>("--- entry with id " + id + " was successfully deleted", HttpStatus.OK);
    }

    @PostMapping("/addActivity/{trackerId}/{activityId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TrackerReadDto> addActivity(
            @PathVariable("trackerId") Long trackerId,
            @PathVariable("activityId") Long activityId) {
        return new ResponseEntity<>(service.addActivity(trackerId, activityId), HttpStatus.CREATED);
    }
}
