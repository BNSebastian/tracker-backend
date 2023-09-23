package com.bsebastian.tracker.app.models.mappers;

import com.bsebastian.tracker.app.models.Activity;
import com.bsebastian.tracker.app.models.ActivityType;
import com.bsebastian.tracker.app.models.dtos.ActivityReadDto;
import com.bsebastian.tracker.app.models.dtos.ActivityTypeReadDto;

public class ActivityMapper {
    public static ActivityReadDto mapToDto(Activity input) {
        ActivityReadDto output = new ActivityReadDto();
        output.setId(input.getId());
        output.setName(input.getName());
        output.setActivityType(input.getActivityType());
        output.setStartedOn(input.getStartedOn());
        output.setUser(input.getUser());
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