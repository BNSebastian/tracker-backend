package com.bsebastian.tracker.core.service;

import com.bsebastian.tracker.core.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserEntity> update(UserEntity userEntity, Long id);
    void delete(UserEntity userEntity);
    Optional<List<UserEntity>> getAll();     // mine
    Boolean checkIfAdmin(Long id);    // mine
}
