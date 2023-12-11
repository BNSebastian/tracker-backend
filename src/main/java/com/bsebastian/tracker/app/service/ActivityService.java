package com.bsebastian.tracker.app.service;

import com.bsebastian.tracker.app.model.dto.ActivityCreateDto;
import com.bsebastian.tracker.app.model.dto.ActivityReadDto;
import com.bsebastian.tracker.app.model.dto.ActivityUpdateDto;

import java.util.HashMap;
import java.util.List;

public interface ActivityService {
    ActivityReadDto create(ActivityCreateDto entry, Long userId, Long typeId);
    List<ActivityReadDto> getAll();
    ActivityReadDto getById(Long id);
    ActivityReadDto update(ActivityUpdateDto entry);
    void delete(Long id);
    List<HashMap<String, Object>> getTime(Long userId);
}
