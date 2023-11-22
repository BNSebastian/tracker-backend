package com.bsebastian.tracker.app.entities.services;

import com.bsebastian.tracker.app.entities.models.dtos.ActivityCreateDto;
import com.bsebastian.tracker.app.entities.models.dtos.ActivityReadDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ActivityService {
    ActivityReadDto create(ActivityCreateDto entry);
    List<ActivityReadDto> getAll();
    ActivityReadDto getById(Long id);
    ActivityReadDto update(ActivityReadDto entry, Long id);
    void delete(Long id);

    ActivityReadDto addActivityType(Long activityId, Long activityTypeId);
    ActivityReadDto setActivityStart(LocalDateTime date, Long id);
    ActivityReadDto setActivityEnd(LocalDateTime date, Long id);
}
