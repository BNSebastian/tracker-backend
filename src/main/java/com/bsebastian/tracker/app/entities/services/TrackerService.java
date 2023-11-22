package com.bsebastian.tracker.app.entities.services;

import com.bsebastian.tracker.app.entities.models.dtos.ActivityReadDto;
import com.bsebastian.tracker.app.entities.models.dtos.TrackerCreateDto;
import com.bsebastian.tracker.app.entities.models.dtos.TrackerReadDto;

import java.util.List;

public interface TrackerService {
    TrackerReadDto create(TrackerCreateDto entry, Long userId);

    List<TrackerReadDto> getAll();

    TrackerReadDto getById(Long id);

    TrackerReadDto update(TrackerReadDto entry, Long id);

    void delete(Long id);

    /*****************************
     * TODO: custom implementations
     *****************************/
    public TrackerReadDto addActivity(Long trackerId, Long activityId);
}
