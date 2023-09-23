package com.bsebastian.tracker.app.models.mappers;

import com.bsebastian.tracker.app.models.ActivityType;
import com.bsebastian.tracker.app.models.dtos.ActivityTypeReadDto;
import com.bsebastian.tracker.app.models.dtos.ActivityTypeCreateDto;

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