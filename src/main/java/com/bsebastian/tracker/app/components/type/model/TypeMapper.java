package com.bsebastian.tracker.app.components.type.model;

public class TypeMapper {
    public static TypeReadDto mapToDto(Type input) {
        TypeReadDto output = new TypeReadDto();
        output.setId(input.getId());
        output.setName(input.getName());
        return output;
    }

    public static Type mapToEntity(TypeReadDto input) {
        Type output = new Type();
        output.setName(input.getName());
        return output;
    }
}