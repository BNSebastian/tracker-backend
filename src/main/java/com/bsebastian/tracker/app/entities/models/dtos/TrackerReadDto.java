package com.bsebastian.tracker.app.entities.models.dtos;

import com.bsebastian.tracker.app.entities.models.Activity;
import com.bsebastian.tracker.security.user.UserEntity;
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
    List<Activity> activities;
    private UserEntity user;
}
