package com.bsebastian.tracker.security.persistence;

import com.bsebastian.tracker.security.model.UserEntity;

import java.util.Optional;

public interface UserService {
    Optional<UserEntity> update(UserEntity userEntity, Long id);
    void delete(UserEntity userEntity);
}
