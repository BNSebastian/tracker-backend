package com.bsebastian.tracker.logic.model.mapper;

import com.bsebastian.tracker.logic.model.Type;
import com.bsebastian.tracker.logic.model.dto.TypeReadDto;

public class TypeMapper {
    public static TypeReadDto mapToDto(Type type) {
        return TypeReadDto.builder()
                          .id(type.getId())
                          .name(type.getName())
                          .build();
    }

    public static Type mapFromDto(TypeReadDto type) {
        return Type.builder()
                   .id(type.getId())
                   .name(type.getName())
                   .build();
    }
}