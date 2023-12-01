package com.bsebastian.tracker.app.components.activity.model;

public class ActivityMapper {
    public static ActivityReadDto mapToDto(Activity input) {
        ActivityReadDto output = new ActivityReadDto();
        output.setId(input.getId());
        output.setName(input.getName());
        //output.setType(input.getType());
//        output.setStartedOn(input.getStartedOn());
        return output;
    }

    public static Activity mapToEntity(ActivityReadDto input) {
        Activity output = new Activity();
        output.setId(input.getId());
        output.setName(input.getName());
        //output.setType(input.getType());
        return output;
    }
}