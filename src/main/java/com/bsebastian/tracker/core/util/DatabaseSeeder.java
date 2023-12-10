package com.bsebastian.tracker.core.util;

import com.bsebastian.tracker.core.model.Role;
import com.bsebastian.tracker.core.model.UserEntity;
import com.bsebastian.tracker.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    public DatabaseSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedUsers();
    }

    private void seedUsers() {
        UserEntity admin = new UserEntity();
        admin.setFirstname("seb");
        admin.setLastname("seb");
        admin.setEmail("seb");
        admin.setPassword("$2a$10$r9ueA6pt.1oLoJRAoOXTYuG7akG9gpCDkKyNHdab2MbF3U8cOdjy2");
        admin.setRole(Role.ADMIN);

        userRepository.saveAll(List.of(admin));
    }
}
