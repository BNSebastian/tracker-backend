package com.bsebastian.tracker.app.components.activity.model;

import com.bsebastian.tracker.app.components.type.model.TypeReadDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data // lombok
@AllArgsConstructor // lombok
@NoArgsConstructor // lombok
@Builder // needed for tests
public class ActivityReadDto {
    private Long id;
    private String name;
    private String description;
    private TypeReadDto type;
    private LocalDateTime startedOn;
    private LocalDateTime endedOn;
    private Long timeElapsedInMinutes;
}