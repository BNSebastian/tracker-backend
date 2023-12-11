package com.bsebastian.tracker.app.service;

import com.bsebastian.tracker.app.model.dto.TypeCreateDto;
import com.bsebastian.tracker.app.model.dto.TypeReadDto;

import java.util.List;

public interface TypeService {
    TypeReadDto create(TypeCreateDto entry);
    List<TypeReadDto> getAll();
    TypeReadDto getById(Long id);
    TypeReadDto update(TypeReadDto entry, Long id);
    void delete(Long id);
}
