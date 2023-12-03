package com.bsebastian.tracker.security.persistence;

import com.bsebastian.tracker.security.model.Role;
import com.bsebastian.tracker.security.model.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.bsebastian.tracker.security.model.Role.ADMIN;

@Service
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

    // mine
    @Override
    public Optional<List<UserEntity>> getAll() {
        List<UserEntity> users = userRepository.findAll();
        return Optional.of(users);
    }
    // mine
    @Override
    public Boolean checkIfAdmin(Long id) {
        UserEntity currentUser = userRepository.findById(id).orElseThrow();
        String role = currentUser.getRole().toString();
        return role.equals("ADMIN");
    }
}
