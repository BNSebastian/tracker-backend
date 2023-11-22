package com.bsebastian.tracker.app.entities.models.mappers;

import com.bsebastian.tracker.app.entities.models.Tracker;
import com.bsebastian.tracker.app.entities.models.dtos.TrackerReadDto;

import java.util.stream.Collectors;

public class TrackerMapper {
    public static TrackerReadDto mapToDto(Tracker input) {
        TrackerReadDto output = new TrackerReadDto();
        output.setId(input.getId());
        output.setName(input.getName());
        // TODO: app specific properties
        output.setActivities(input.getActivities()
                                     .stream()
                                     .map(ActivityMapper::mapToDto)
                                     .collect(Collectors.toList()));
        return output;
    }
}
