package com.bsebastian.tracker.security.persistence;

import com.bsebastian.tracker.security.model.UserEntity;

import java.util.Optional;

public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserEntity> update(UserEntity userEntity, Long id) {
        Optional<UserEntity> currentUser = userRepository.findById(id);
        currentUser = Optional.ofNullable(userEntity);
        return currentUser;
    }

    @Override
    public void delete(UserEntity userEntity) {
        userRepository.delete(userEntity);
    }
}
