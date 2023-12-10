package com.bsebastian.tracker.app.components.activity.persistence;

import com.bsebastian.tracker.app.components.activity.model.*;
import com.bsebastian.tracker.app.components.activity.ActivityException;
import com.bsebastian.tracker.app.components.type.model.Type;
import com.bsebastian.tracker.app.components.type.persistence.TypeRepository;
import com.bsebastian.tracker.core.model.UserEntity;
import com.bsebastian.tracker.core.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
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
    public List<HashMap<String, Object>> getTime(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow();
        List<Activity> activities = user.getActivities();
        List<HashMap<String, Object>> resultList = new ArrayList<>();

        for (Activity current : activities) {
            String activityType = current.getType().getName();
            String month = capitalizeFirstLetter(current.getStartedOn().getMonth().toString().toLowerCase()); // Capitalizing the first letter of the month
            int year = current.getStartedOn().getYear();

            // Check if there's an existing entry for the same month and year
            boolean found = false;
            for (HashMap<String, Object> entry : resultList) {
                if ((int) entry.get("year") == year && entry.get("month").equals(month)) {
                    @SuppressWarnings("unchecked")
                    List<HashMap<String, Object>> activitiesList = (List<HashMap<String, Object>>) entry.get("activities");

                    // Check if the activity already exists in the activities list for the current month
                    boolean activityFound = false;
                    for (HashMap<String, Object> activity : activitiesList) {
                        if (activity.get("name").equals(activityType)) {
                            activity.put("time", (Long) activity.get("time") + current.getTimeElapsedInMinutes());
                            activityFound = true;
                            break;
                        }
                    }

                    // If the activity doesn't exist, add it to the activities list
                    if (!activityFound) {
                        HashMap<String, Object> newActivity = new HashMap<>();
                        newActivity.put("name", activityType);
                        newActivity.put("time", current.getTimeElapsedInMinutes());
                        activitiesList.add(newActivity);
                    }

                    found = true;
                    break;
                }
            }

            // If no entry exists, create a new one
            if (!found) {
                HashMap<String, Object> newEntry = new HashMap<>();
                newEntry.put("year", year);
                newEntry.put("month", month);
                List<HashMap<String, Object>> activitiesList = new ArrayList<>();
                HashMap<String, Object> newActivity = new HashMap<>();
                newActivity.put("name", activityType);
                newActivity.put("time", current.getTimeElapsedInMinutes());
                activitiesList.add(newActivity);
                newEntry.put("activities", activitiesList);
                resultList.add(newEntry);
            }
        }

        return resultList;
    }

    public String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input; // Handle null or empty string
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }



}
