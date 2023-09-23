package com.bsebastian.tracker.app.entities.services;

import com.bsebastian.tracker.app.entities.models.dtos.TrackerCreateDto;
import com.bsebastian.tracker.app.entities.models.dtos.TrackerReadDto;

import java.util.List;

public interface TrackerService {
    TrackerReadDto create(TrackerCreateDto entry);
    List<TrackerReadDto> getAll();
    TrackerReadDto getById(Long id);
    TrackerReadDto update(TrackerReadDto entry, Long id);
    void delete(Long id);
}
