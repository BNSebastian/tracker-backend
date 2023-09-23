package com.bsebastian.tracker.app.entities.services.implementations;

import com.bsebastian.tracker.app.entities.exceptions.NotFoundException;
import com.bsebastian.tracker.app.entities.models.Tracker;

import com.bsebastian.tracker.app.entities.models.dtos.TrackerCreateDto;
import com.bsebastian.tracker.app.entities.models.dtos.TrackerReadDto;
import com.bsebastian.tracker.app.entities.models.mappers.TrackerMapper;
import com.bsebastian.tracker.app.entities.repositories.TrackerRepository;
import com.bsebastian.tracker.app.entities.services.TrackerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackerServiceImpl implements TrackerService {

    private final TrackerRepository trackerRepository;

    public TrackerServiceImpl(TrackerRepository trackerRepository) {
        this.trackerRepository = trackerRepository;
    }

    @Override
    public TrackerReadDto create(TrackerCreateDto entry) {
        Tracker response = new Tracker();
        response.setName(entry.getName());

        Tracker savedResponse = trackerRepository.save(response);

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
        output.setUser(entryDto.getUser());
        output.setActivities(entryDto.getActivities());

        Tracker updatedOutput = trackerRepository.save(output);

        return TrackerMapper.mapToDto(updatedOutput);
    }

    @Override
    public void delete(Long id) {
        Tracker output = trackerRepository.findById(id)
                                          .orElseThrow(() -> new NotFoundException("the entry with id: " + id + " wasn't found"));
        trackerRepository.delete(output);
    }
}
