package com.bsebastian.tracker.app.components.tracker.persistence;

import com.bsebastian.tracker.app.components.tracker.model.TrackerCreateDto;
import com.bsebastian.tracker.app.components.tracker.model.TrackerReadDto;

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
