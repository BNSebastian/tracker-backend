package com.bsebastian.tracker.app.entities.ActivityType.model.mapper;

import com.bsebastian.tracker.app.entities.ActivityType.model.ActivityType;
import com.bsebastian.tracker.app.entities.ActivityType.model.dto.ActivityTypeReadDto;

public class ActivityTypeMapper {
    public static ActivityTypeReadDto mapToDto(ActivityType input) {
        ActivityTypeReadDto output = new ActivityTypeReadDto();
        output.setId(input.getId());
        output.setName(input.getName());
        return output;
    }

    public static ActivityType mapToEntity(ActivityTypeReadDto input) {
        ActivityType output = new ActivityType();
        output.setName(input.getName());
        return output;
    }
}