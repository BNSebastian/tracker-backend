package com.bsebastian.tracker.app.entities.ActivityType.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // lombok
@AllArgsConstructor // lombok
@NoArgsConstructor // lombok
@Builder // needed for tests
public class ActivityTypeReadDto {
    private Long id;
    private String name;
}