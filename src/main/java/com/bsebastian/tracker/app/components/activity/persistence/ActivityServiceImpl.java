package com.bsebastian.tracker.app.components.activity.persistence;

import com.bsebastian.tracker.app.components.activity.model.*;
import com.bsebastian.tracker.app.components.activity.ActivityException;
import com.bsebastian.tracker.app.components.type.model.Type;
import com.bsebastian.tracker.app.components.type.model.TypeMapper;
import com.bsebastian.tracker.app.components.type.model.TypeReadDto;
import com.bsebastian.tracker.app.components.type.persistence.TypeRepository;
import com.bsebastian.tracker.security.model.UserEntity;
import com.bsebastian.tracker.security.persistence.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;
    private final TypeRepository typeRepository;
    private final UserRepository userRepository;

    public ActivityServiceImpl(ActivityRepository repository, TypeRepository typeRepository, UserRepository userRepository) {
        this.activityRepository = repository;
        this.typeRepository = typeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ActivityReadDto create(ActivityCreateDto sentActivity, Long userId, Long typeId) {
        UserEntity user = userRepository.findById(userId).orElseThrow();
        Type type = typeRepository.findById(typeId).orElseThrow();

        Long timeElapsedInMinutes = Duration
                .between(sentActivity.getStartedOn(), sentActivity.getEndedOn())
                .toMinutes();

        Activity newActivity = Activity.builder()
                .name(sentActivity.getName())
                .description(sentActivity.getDescription())
                .startedOn(sentActivity.getStartedOn())
                .endedOn(sentActivity.getEndedOn())
                .timeElapsedInMinutes(timeElapsedInMinutes)
                .userEntity(user)
                .type(type)
                .build();

        Activity savedActivity = activityRepository.save(newActivity);

        return ActivityMapper.mapToDto(savedActivity);
    }

    @Override
    public List<ActivityReadDto> getAll() {
        List<Activity> list = activityRepository.findAll();
        return list.stream()
                   .map(ActivityMapper::mapToDto)
                   .collect(Collectors.toList());
    }

    @Override
    public ActivityReadDto getById(Long id) {
        Activity Activity = activityRepository.findById(id)
                                              .orElseThrow(() -> new ActivityException("the entry with id: " + id + " wasn't found"));
        return ActivityMapper.mapToDto(Activity);
    }

    @Override
    public ActivityReadDto update(ActivityUpdateDto input) {
        Activity activity = activityRepository.findById(input.getId()).orElseThrow();

        activity.setName(input.getName());
        activity.setDescription(input.getDescription());
        activity.setStartedOn(input.getStartedOn());
        activity.setEndedOn(input.getEndedOn());
        activity.setTimeElapsedInMinutes(
                Duration.between(input.getStartedOn(), input.getEndedOn())
                        .toMinutes()
        );

        Activity updatedActivity = activityRepository.save(activity);
        return ActivityMapper.mapToDto(updatedActivity);
    }

    @Override
    public void delete(Long id) {
        Activity Activity = activityRepository.findById(id)
                                              .orElseThrow(() -> new ActivityException("the entry with id: " + id + " wasn't found"));
        activityRepository.delete(Activity);
    }

    @Override
    public Long getTotalTime(Long userId) {
        Long total = 0L;
        List<Activity> activities = activityRepository.findAll();
        for (Activity current : activities) {
            if (current.getUserEntity().getId().equals(userId)) {
                total += current.getTimeElapsedInMinutes();
            } else {
                throw new RuntimeException("User not found in activities");
            }
        }
        return total;
    }
}
