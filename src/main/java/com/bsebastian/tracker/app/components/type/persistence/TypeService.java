package com.bsebastian.tracker.app.components.type.persistence;

import com.bsebastian.tracker.app.components.type.model.TypeCreateDto;
import com.bsebastian.tracker.app.components.type.model.TypeReadDto;

import java.util.List;

public interface TypeService {
    TypeReadDto create(TypeCreateDto entry);
    List<TypeReadDto> getAll();
    TypeReadDto getById(Long id);
    TypeReadDto update(TypeReadDto entry, Long id);
    void delete(Long id);
}
