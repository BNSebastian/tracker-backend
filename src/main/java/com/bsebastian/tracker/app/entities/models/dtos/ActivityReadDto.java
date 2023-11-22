package com.bsebastian.tracker.app.entities.models.dtos;

import com.bsebastian.tracker.app.entities.models.ActivityType;
import com.bsebastian.tracker.security.user.UserEntity;
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
    private ActivityType activityType;
    private LocalDateTime startedOn;
}
