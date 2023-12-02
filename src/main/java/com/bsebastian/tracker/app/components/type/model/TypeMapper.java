package com.bsebastian.tracker.app.components.type.model;

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