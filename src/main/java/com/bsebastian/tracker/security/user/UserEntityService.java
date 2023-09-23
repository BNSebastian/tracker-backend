package com.bsebastian.tracker.security.user;

import com.bsebastian.tracker.app.models.Activity;
import com.bsebastian.tracker.app.models.dtos.ActivityCreateDto;
import com.bsebastian.tracker.app.models.dtos.ActivityReadDto;

import java.util.List;

public interface UserEntityService {

    UserEntity addActivity(Long userId, Long activityId);
    void delete(Long id);

}