package com.bsebastian.tracker.logic.controller;

import com.bsebastian.tracker.logic.model.dto.TypeCreateDto;
import com.bsebastian.tracker.logic.model.dto.TypeReadDto;

import com.bsebastian.tracker.logic.service.TypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/type")
public class TypeController {
    private final TypeService service;


    public TypeController(TypeService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<TypeReadDto>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeReadDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getById(id),HttpStatus.OK);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TypeReadDto> create(@RequestBody TypeCreateDto inputDto) {
        return new ResponseEntity<>(service.create(inputDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeReadDto> update(@RequestBody TypeReadDto inputDto, @PathVariable("id") Long id) {
        return new ResponseEntity<>(service.update(inputDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>("--- entry with id " + id + " was successfully deleted", HttpStatus.OK);
    }
}
