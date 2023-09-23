package com.bsebastian.tracker.app.persistence.services.implementations;

import com.bsebastian.tracker.app.models.Activity;
import com.bsebastian.tracker.app.models.ActivityType;
import com.bsebastian.tracker.app.models.dtos.ActivityCreateDto;
import com.bsebastian.tracker.app.models.dtos.ActivityReadDto;
import com.bsebastian.tracker.app.models.dtos.ActivityTypeReadDto;
import com.bsebastian.tracker.app.models.exceptions.ActivityNotFoundException;
import com.bsebastian.tracker.app.models.exceptions.ActivityTypeNotFoundException;
import com.bsebastian.tracker.app.models.mappers.ActivityMapper;
import com.bsebastian.tracker.app.models.mappers.ActivityTypeMapper;
import com.bsebastian.tracker.app.persistence.repositories.ActivityRepository;
import com.bsebastian.tracker.app.persistence.repositories.ActivityTypeRepository;
import com.bsebastian.tracker.app.persistence.services.ActivityService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;
    private final ActivityTypeRepository activityTypeRepository;

    public ActivityServiceImpl(ActivityRepository repository, ActivityTypeRepository activityTypeRepository) {
        this.activityRepository = repository;
        this.activityTypeRepository = activityTypeRepository;
    }

    @Override
    public ActivityReadDto create(ActivityCreateDto entry) {
        Activity response = new Activity();
        response.setName(entry.getName());

        Activity savedResponse = activityRepository.save(response);

        ActivityReadDto responseDto = new ActivityReadDto();
        responseDto.setId(savedResponse.getId());
        responseDto.setName(savedResponse.getName());

        return responseDto;
    }

    @Override
    public List<ActivityReadDto> getAll() {
        List<Activity> list = activityRepository.findAll();
        return list.stream().map(current -> ActivityMapper.mapToDto(current)).collect(Collectors.toList());
    }

    @Override
    public ActivityReadDto getById(Long id) {
        Activity Activity = activityRepository.findById(id).orElseThrow(() -> new ActivityNotFoundException("the entry with id: " + id + " wasn't found"));
        return ActivityMapper.mapToDto(Activity);
    }

    @Override
    public ActivityReadDto update(ActivityReadDto entryDto, Long id) {
        Activity Activity = activityRepository.findById(id).orElseThrow(() -> new ActivityNotFoundException("the entry with id: " + id + " wasn't found"));
        Activity.setName(entryDto.getName());
        Activity updatedActivity = activityRepository.save(Activity);
        return ActivityMapper.mapToDto(updatedActivity);
    }

    @Override
    public void delete(Long id) {
        Activity Activity = activityRepository.findById(id)
                                              .orElseThrow(() -> new ActivityNotFoundException("the entry with id: " + id + " wasn't found"));
        activityRepository.delete(Activity);
    }

    @Override
    public ActivityReadDto addActivityType(Long activityId, Long activityTypeId) {
        // Find the activity with the given ID, or throw an exception if not found
        Activity activity = activityRepository.findById(activityId)
                                              .orElseThrow(() -> new ActivityNotFoundException("the entry with id: " + activityId + " wasn't found"));

        // Find the activity type with the given ID, or throw an exception if not found
        ActivityType activityType = activityTypeRepository.findById(activityTypeId)
                                                          .orElseThrow(() -> new ActivityTypeNotFoundException("the entry with id: " + activityTypeId + " wasn't found"));

        // Set the activity type for the activity
        activity.setActivityType(activityType);

        // Save the updated activity in the database
        Activity updatedActivity = activityRepository.save(activity);

        // Convert the updated activity to a DTO (Data Transfer Object) for response
        return ActivityMapper.mapToDto(updatedActivity);
    }

    @Override
    public ActivityReadDto setActivityStart(LocalDateTime date, Long id) {
        Activity activity = activityRepository.findById(id)
                                              .orElseThrow(() -> new ActivityNotFoundException("the entry with id: " + id + " wasn't found"));
        activity.setStartedOn(date);
        activityRepository.save(activity);
        return ActivityMapper.mapToDto(activity);
    }

    @Override
    public ActivityReadDto setActivityEnd(LocalDateTime date, Long id) {
        Activity activity = activityRepository.findById(id)
                                              .orElseThrow(() -> new ActivityNotFoundException("the entry with id: " + id + " wasn't found"));
        activity.setEndedOn(date);
        activityRepository.save(activity);
        return ActivityMapper.mapToDto(activity);
    }

}
