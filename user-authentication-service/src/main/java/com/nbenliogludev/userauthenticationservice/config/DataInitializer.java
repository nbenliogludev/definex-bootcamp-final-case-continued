package com.nbenliogludev.userauthenticationservice.config;

import com.nbenliogludev.userauthenticationservice.entity.Role;
import com.nbenliogludev.userauthenticationservice.entity.User;
import com.nbenliogludev.userauthenticationservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        String defaultEmail = "groupmanager@company.com";
        if (userRepository.findByEmail(defaultEmail).isEmpty()) {

            User user = User.builder()
                    .firstname("Group")
                    .lastname("Manager")
                    .email(defaultEmail)
                    .password(passwordEncoder.encode("12345"))
                    .role(Role.GROUP_PROJECT_MANAGER)
                    .build();

            userRepository.save(user);
            System.out.println("Created default GROUP_PROJECT_MANAGER user: " + defaultEmail);
        } else {
            System.out.println("GROUP_PROJECT_MANAGER user already exists.");
        }
    }
}
