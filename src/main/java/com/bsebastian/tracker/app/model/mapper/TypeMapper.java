package com.bsebastian.tracker.app.model.mapper;

import com.bsebastian.tracker.app.model.Type;
import com.bsebastian.tracker.app.model.dto.TypeReadDto;

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