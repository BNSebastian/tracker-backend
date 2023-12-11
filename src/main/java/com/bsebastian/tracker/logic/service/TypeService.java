package com.bsebastian.tracker.logic.service;

import com.bsebastian.tracker.logic.model.dto.TypeCreateDto;
import com.bsebastian.tracker.logic.model.dto.TypeReadDto;

import java.util.List;

public interface TypeService {
    TypeReadDto create(TypeCreateDto entry);
    List<TypeReadDto> getAll();
    TypeReadDto getById(Long id);
    TypeReadDto update(TypeReadDto entry, Long id);
    void delete(Long id);
}
