package com.bsebastian.tracker.app.components.activity.persistence;

import com.bsebastian.tracker.app.components.activity.model.ActivityCreateDto;
import com.bsebastian.tracker.app.components.activity.model.ActivityReadDto;
import com.bsebastian.tracker.app.components.activity.model.ActivityUpdateDto;

import java.util.List;

public interface ActivityService {
    ActivityReadDto create(ActivityCreateDto entry, Long userId, Long typeId);
    List<ActivityReadDto> getAll();
    ActivityReadDto getById(Long id);
    ActivityReadDto update(ActivityUpdateDto entry);
    void delete(Long id);

 //   ActivityReadDto addType(Long activityId, Long typeId);
//    ActivityReadDto setActivityStart(LocalDateTime date, Long id);
//    ActivityReadDto setActivityEnd(LocalDateTime date, Long id);
}
