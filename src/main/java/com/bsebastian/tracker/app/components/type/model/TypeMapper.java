package com.bsebastian.tracker.app.components.type.model;

public class TypeMapper {
    public static TypeReadDto mapToDto(Type type) {
        return TypeReadDto.builder()
                .id(type.getId())
                .name(type.getName())
                .build();
    }
}