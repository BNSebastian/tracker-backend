package com.bsebastian.tracker.app.entities.services.implementations;

import com.bsebastian.tracker.app.entities.models.ActivityType;
import com.bsebastian.tracker.app.entities.models.dtos.ActivityTypeCreateDto;
import com.bsebastian.tracker.app.entities.models.dtos.ActivityTypeReadDto;
import com.bsebastian.tracker.app.entities.exceptions.ActivityTypeNotFoundException;
import com.bsebastian.tracker.app.entities.models.mappers.ActivityTypeMapper;
import com.bsebastian.tracker.app.entities.repositories.ActivityTypeRepository;
import com.bsebastian.tracker.app.entities.services.ActivityTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityTypeServiceImpl implements ActivityTypeService {
    private final ActivityTypeRepository activityTypeRepository;

    public ActivityTypeServiceImpl(ActivityTypeRepository repository) {
        this.activityTypeRepository = repository;
    }

    @Override
    public ActivityTypeReadDto create(ActivityTypeCreateDto entry) {
        ActivityType response = new ActivityType();
        response.setName(entry.getName());

        ActivityType savedResponse = activityTypeRepository.save(response);

        ActivityTypeReadDto responseDto = new ActivityTypeReadDto();
        responseDto.setId(savedResponse.getId());
        responseDto.setName(savedResponse.getName());

        return responseDto;
    }

    @Override
    public List<ActivityTypeReadDto> getAll() {
        List<ActivityType> list = activityTypeRepository.findAll();
        return list.stream()
                   .map(current -> ActivityTypeMapper.mapToDto(current))
                   .collect(Collectors.toList());
    }

    @Override
    public ActivityTypeReadDto getById(Long id) {
        ActivityType activityType = activityTypeRepository.findById(id)
                                                          .orElseThrow(() -> new ActivityTypeNotFoundException("the entry with id: " + id + " wasn't found"));
        return ActivityTypeMapper.mapToDto(activityType);
    }

    @Override
    public ActivityTypeReadDto update(ActivityTypeReadDto entryDto, Long id) {
        ActivityType activityType = activityTypeRepository.findById(id)
                                                          .orElseThrow(() -> new ActivityTypeNotFoundException("the entry with id: " + id + " wasn't found"));
        activityType.setName(entryDto.getName());
        ActivityType updatedActivityType = activityTypeRepository.save(activityType);
        return ActivityTypeMapper.mapToDto(updatedActivityType);
    }

    @Override
    public void delete(Long id) {
        ActivityType activityType = activityTypeRepository.findById(id)
                                                          .orElseThrow(() -> new ActivityTypeNotFoundException("the entry with id: " + id + " wasn't found"));
        activityTypeRepository.delete(activityType);
    }
}
