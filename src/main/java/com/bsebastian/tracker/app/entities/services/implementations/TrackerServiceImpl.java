package com.bsebastian.tracker.app.entities.services.implementations;

import com.bsebastian.tracker.app.entities.exceptions.NotFoundException;
import com.bsebastian.tracker.app.entities.models.Activity;
import com.bsebastian.tracker.app.entities.models.Tracker;

import com.bsebastian.tracker.app.entities.models.dtos.TrackerCreateDto;
import com.bsebastian.tracker.app.entities.models.dtos.TrackerReadDto;
import com.bsebastian.tracker.app.entities.models.mappers.TrackerMapper;
import com.bsebastian.tracker.app.entities.repositories.ActivityRepository;
import com.bsebastian.tracker.app.entities.repositories.TrackerRepository;
import com.bsebastian.tracker.app.entities.services.TrackerService;
import com.bsebastian.tracker.security.user.UserEntity;
import com.bsebastian.tracker.security.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackerServiceImpl implements TrackerService {

    private final TrackerRepository trackerRepository;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;

    public TrackerServiceImpl(
            TrackerRepository trackerRepository,
            UserRepository userRepository,
            ActivityRepository activityRepository
    ) {
        this.trackerRepository = trackerRepository;
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
    }

    @Override
    public TrackerReadDto create(TrackerCreateDto entry, Long userId) {
        UserEntity user = userRepository.findById(userId)
                                        .orElseThrow();

        Tracker response = new Tracker();
        response.setName(entry.getName());
        response.setUser(user);

        Tracker savedResponse = trackerRepository.save(response);
        user.setTracker(savedResponse);
        userRepository.save(user);

        TrackerReadDto responseDto = new TrackerReadDto();
        responseDto.setId(savedResponse.getId());
        responseDto.setName(savedResponse.getName());

        return responseDto;
    }

    @Override
    public List<TrackerReadDto> getAll() {
        List<Tracker> outputList = trackerRepository.findAll();
        return outputList.stream()
                         .map(TrackerMapper::mapToDto)
                         .collect(Collectors.toList());
    }

    @Override
    public TrackerReadDto getById(Long id) {
        Tracker output = trackerRepository.findById(id)
                                          .orElseThrow(() -> new NotFoundException("the entry with id: " + id + " wasn't found"));
        return TrackerMapper.mapToDto(output);
    }

    @Override
    public TrackerReadDto update(TrackerReadDto entryDto, Long id) {
        Tracker output = trackerRepository.findById(id)
                                          .orElseThrow(() -> new NotFoundException("the entry with id: " + id + " wasn't found"));

        output.setName(entryDto.getName());
        //output.setUser(entryDto.getUser());
        // TODO
        //output.setActivities(entryDto.getActivities());

        Tracker updatedOutput = trackerRepository.save(output);

        return TrackerMapper.mapToDto(updatedOutput);
    }

    @Override
    public void delete(Long id) {
        Tracker output = trackerRepository.findById(id)
                                          .orElseThrow(() -> new NotFoundException("the entry with id: " + id + " wasn't found"));
        trackerRepository.delete(output);
    }

    @Override
    public TrackerReadDto addActivity(Long trackerId, Long activityId) {
        Tracker tracker = trackerRepository.findById(trackerId)
                                           .orElseThrow(() -> new NotFoundException("the entry with id: " + trackerId + " wasn't found"));
        Activity activity = activityRepository.findById(activityId)
                                              .orElseThrow(() -> new NotFoundException("the entry with id: " + trackerId + " wasn't found"));

        List<Activity> activitiesList = tracker.getActivities();

        activitiesList.add(activity);

        tracker.setActivities(activitiesList);

        Tracker output = trackerRepository.save(tracker);

        return TrackerMapper.mapToDto(output);
    }
}
