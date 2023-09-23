package com.bsebastian.tracker.app.entities.Activity.model.mapper;

import com.bsebastian.tracker.app.entities.Activity.model.Activity;
import com.bsebastian.tracker.app.entities.Activity.model.dto.ActivityReadDto;

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