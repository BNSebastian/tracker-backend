package com.bsebastian.tracker.app.components.activity.persistence;

import com.bsebastian.tracker.app.components.activity.model.Activity;
import com.bsebastian.tracker.app.components.activity.model.ActivityCreateDto;
import com.bsebastian.tracker.app.components.activity.model.ActivityReadDto;
import com.bsebastian.tracker.app.components.activity.ActivityException;
import com.bsebastian.tracker.app.components.activity.model.ActivityMapper;
import com.bsebastian.tracker.app.components.type.model.Type;
import com.bsebastian.tracker.app.components.type.model.TypeReadDto;
import com.bsebastian.tracker.app.components.type.persistence.TypeRepository;
import com.bsebastian.tracker.security.model.UserEntity;
import com.bsebastian.tracker.security.persistence.UserRepository;
import org.springframework.stereotype.Service;

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

        Activity newActivity = Activity.builder()
                .name(sentActivity.getName())
                .description(sentActivity.getDescription())
                .startedOn(sentActivity.getStartedOn())
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
    public ActivityReadDto update(ActivityReadDto entryDto, Long id) {
        Activity Activity = activityRepository.findById(id).orElseThrow(() -> new ActivityException("the entry with id: " + id + " wasn't found"));
        Activity.setName(entryDto.getName());
        Activity updatedActivity = activityRepository.save(Activity);
        return ActivityMapper.mapToDto(updatedActivity);
    }

    @Override
    public void delete(Long id) {
        Activity Activity = activityRepository.findById(id)
                                              .orElseThrow(() -> new ActivityException("the entry with id: " + id + " wasn't found"));
        activityRepository.delete(Activity);
    }

//    @Override
//    public ActivityReadDto addType(Long activityId, Long activityTypeId) {
//        // Find the activity with the given ID, or throw an exception if not found
//        Activity activity = activityRepository.findById(activityId)
//                                              .orElseThrow(() -> new ActivityException("the entry with id: " + activityId + " wasn't found"));
//
//        // Find the activity type with the given ID, or throw an exception if not found
//        Type type = typeRepository.findById(activityTypeId)
//                                  .orElseThrow(() -> new TypeException("the entry with id: " + activityTypeId + " wasn't found"));
//
//        // Set the activity type for the activity
//        //activity.setType(type);
//
//        // Save the updated activity in the database
//        Activity updatedActivity = activityRepository.save(activity);
//
//        // Convert the updated activity to a DTO (Data Transfer Object) for response
//        return ActivityMapper.mapToDto(updatedActivity);
//    }

//    @Override
//    public ActivityReadDto setActivityStart(LocalDateTime date, Long id) {
//        Activity activity = activityRepository.findById(id)
//                                              .orElseThrow(() -> new ActivityException("the entry with id: " + id + " wasn't found"));
//        activity.setStartedOn(date);
//        activityRepository.save(activity);
//        return ActivityMapper.mapToDto(activity);
//    }

//    @Override
//    public ActivityReadDto setActivityEnd(LocalDateTime date, Long id) {
//        Activity activity = activityRepository.findById(id)
//                                              .orElseThrow(() -> new ActivityException("the entry with id: " + id + " wasn't found"));
//        activity.setEndedOn(date);
//        activityRepository.save(activity);
//        return ActivityMapper.mapToDto(activity);
//    }

}
