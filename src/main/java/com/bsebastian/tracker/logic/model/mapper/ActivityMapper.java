package com.bsebastian.tracker.logic.model.mapper;

import com.bsebastian.tracker.logic.model.dto.ActivityReadDto;
import com.bsebastian.tracker.logic.model.Activity;

public class ActivityMapper {
    public static ActivityReadDto mapToDto(Activity activity) {
        return ActivityReadDto.builder()
                .id(activity.getId())
                .name(activity.getName())
                .description(activity.getDescription())
                .type(TypeMapper.mapToDto(activity.getType()))
                .startedOn(activity.getStartedOn())
                .endedOn(activity.getEndedOn())
                .timeElapsedInMinutes(activity.getTimeElapsedInMinutes())
                .user(activity.getUserEntity().getEmail())
                .build();
    }
}