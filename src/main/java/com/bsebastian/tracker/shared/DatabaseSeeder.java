package com.bsebastian.tracker.shared;

import com.bsebastian.tracker.logic.model.Type;
import com.bsebastian.tracker.logic.repository.TypeRepository;
import com.bsebastian.tracker.security.model.Role;
import com.bsebastian.tracker.security.model.UserEntity;
import com.bsebastian.tracker.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TypeRepository typeRepository;
    @Autowired
    public DatabaseSeeder(UserRepository userRepository, TypeRepository typeRepository) {
        this.userRepository = userRepository;
        this.typeRepository = typeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedUsers();
    }

    private void seedUsers() {
        UserEntity admin = new UserEntity();
        admin.setFirstname("seb");
        admin.setLastname("cheneb");
        admin.setEmail("seb@cheneb.com");
        admin.setPassword("$2a$10$r9ueA6pt.1oLoJRAoOXTYuG7akG9gpCDkKyNHdab2MbF3U8cOdjy2");
        admin.setRole(Role.ADMIN);

        userRepository.saveAll(List.of(admin));

        Type type1 = new Type();
        type1.setName("working");

        Type type2 = new Type();
        type2.setName("traveling");

        Type type3 = new Type();
        type3.setName("eating");

        typeRepository.saveAll(List.of(type1, type2, type3));

    }
}
