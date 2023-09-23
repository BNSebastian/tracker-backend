package com.bsebastian.tracker.app.entities.models.mappers;

import com.bsebastian.tracker.app.entities.models.Tracker;
import com.bsebastian.tracker.app.entities.models.dtos.TrackerReadDto;

public class TrackerMapper {
    public static TrackerReadDto mapToDto(Tracker input) {
        TrackerReadDto output = new TrackerReadDto();
        output.setId(input.getId());
        output.setName(input.getName());
        output.setUser(input.getUser());
        output.setActivities(input.getActivities());
        return output;
    }

    public static Tracker mapToEntity(TrackerReadDto input) {
        Tracker output = new Tracker();
        output.setId(input.getId());
        output.setName(input.getName());
        output.setUser(input.getUser());
        output.setActivities(input.getActivities());
        return output;
    }
}
