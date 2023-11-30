package com.bsebastian.tracker.app.components.tracker.model;

import com.bsebastian.tracker.app.components.activity.model.ActivityReadDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data // lombok
@AllArgsConstructor // lombok
@NoArgsConstructor // lombok
@Builder // needed for tests
public class TrackerReadDto {
    private Long id;
    private String name;
    // TODO: app specific properties
    List<ActivityReadDto> activities;
}
