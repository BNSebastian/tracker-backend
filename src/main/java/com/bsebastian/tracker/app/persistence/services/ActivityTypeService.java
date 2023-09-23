package com.bsebastian.tracker.app.persistence.services;

import com.bsebastian.tracker.app.models.dtos.ActivityTypeCreateDto;
import com.bsebastian.tracker.app.models.dtos.ActivityTypeReadDto;

import java.util.List;

public interface ActivityTypeService {
    ActivityTypeReadDto create(ActivityTypeCreateDto entry);
    List<ActivityTypeReadDto> getAll();
    ActivityTypeReadDto getById(Long id);
    ActivityTypeReadDto update(ActivityTypeReadDto entry, Long id);
    void delete(Long id);
}
