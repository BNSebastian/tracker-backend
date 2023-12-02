package com.bsebastian.tracker.app.components.activity.model;

import com.bsebastian.tracker.app.components.type.model.TypeMapper;

public class ActivityMapper {
    public static ActivityReadDto mapToDto(Activity activity) {
        return ActivityReadDto.builder()
                .id(activity.getId())
                .name(activity.getName())
                .type(TypeMapper.mapToDto(activity.getType()))
                .build();
    }
}