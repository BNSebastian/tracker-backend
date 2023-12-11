package com.bsebastian.tracker.logic.service;

import com.bsebastian.tracker.logic.model.dto.ActivityCreateDto;
import com.bsebastian.tracker.logic.model.dto.ActivityReadDto;
import com.bsebastian.tracker.logic.model.dto.ActivityUpdateDto;

import java.util.HashMap;
import java.util.List;

public interface ActivityService {
    ActivityReadDto create(ActivityCreateDto entry, Long userId, Long typeId);
    List<ActivityReadDto> getAll();
    List<ActivityReadDto> getAllByUserId(Long userId);
    ActivityReadDto getById(Long id);
    ActivityReadDto update(ActivityUpdateDto entry);
    void delete(Long id);
    List<HashMap<String, Object>> getTime(Long userId);
}
