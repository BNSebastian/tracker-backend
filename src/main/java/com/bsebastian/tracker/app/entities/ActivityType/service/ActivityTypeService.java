package com.bsebastian.tracker.app.entities.ActivityType.service;

import com.bsebastian.tracker.app.entities.ActivityType.model.dto.ActivityTypeCreateDto;
import com.bsebastian.tracker.app.entities.ActivityType.model.dto.ActivityTypeReadDto;

import java.util.List;

public interface ActivityTypeService {
    ActivityTypeReadDto create(ActivityTypeCreateDto entry);
    List<ActivityTypeReadDto> getAll();
    ActivityTypeReadDto getById(Long id);
    ActivityTypeReadDto update(ActivityTypeReadDto entry, Long id);
    void delete(Long id);
}
