package com.bsebastian.tracker.app.components.type.persistence;

import com.bsebastian.tracker.app.components.type.model.Type;
import com.bsebastian.tracker.app.components.type.model.TypeCreateDto;
import com.bsebastian.tracker.app.components.type.model.TypeReadDto;
import com.bsebastian.tracker.app.components.type.TypeException;
import com.bsebastian.tracker.app.components.type.model.TypeMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeServiceImpl implements TypeService {
    private final TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository repository) {
        this.typeRepository = repository;
    }

    @Override
    public TypeReadDto create(TypeCreateDto entry) {
        Type response = new Type();
        response.setName(entry.getName());

        Type savedResponse = typeRepository.save(response);

        TypeReadDto responseDto = new TypeReadDto();
        responseDto.setId(savedResponse.getId());
        responseDto.setName(savedResponse.getName());

        return responseDto;
    }

    @Override
    public List<TypeReadDto> getAll() {
        List<Type> list = typeRepository.findAll();
        return list.stream()
                   .map(current -> TypeMapper.mapToDto(current))
                   .collect(Collectors.toList());
    }

    @Override
    public TypeReadDto getById(Long id) {
        Type type = typeRepository.findById(id)
                                  .orElseThrow(() -> new TypeException("the entry with id: " + id + " wasn't found"));
        return TypeMapper.mapToDto(type);
    }

    @Override
    public TypeReadDto update(TypeReadDto entryDto, Long id) {
        Type type = typeRepository.findById(id)
                                  .orElseThrow(() -> new TypeException("the entry with id: " + id + " wasn't found"));
        type.setName(entryDto.getName());
        Type updatedType = typeRepository.save(type);
        return TypeMapper.mapToDto(updatedType);
    }

    @Override
    public void delete(Long id) {
        Type type = typeRepository.findById(id)
                                  .orElseThrow(() -> new TypeException("the entry with id: " + id + " wasn't found"));
        typeRepository.delete(type);
    }
}
