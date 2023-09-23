package com.bsebastian.tracker.app.entities.models.mappers;

import com.bsebastian.tracker.app.entities.models.Activity;
import com.bsebastian.tracker.app.entities.models.dtos.ActivityReadDto;

public class ActivityMapper {
    public static ActivityReadDto mapToDto(Activity input) {
        ActivityReadDto output = new ActivityReadDto();
        output.setId(input.getId());
        output.setName(input.getName());
        output.setActivityType(input.getActivityType());
        output.setStartedOn(input.getStartedOn());
        return output;
    }

    public static Activity mapToEntity(ActivityReadDto input) {
        Activity output = new Activity();
        output.setId(input.getId());
        output.setName(input.getName());
        output.setActivityType(input.getActivityType());
        return output;
    }
}