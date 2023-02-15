package com.example.restaurant.config;

import com.example.restaurant.entity.Role;
import com.example.restaurant.entity.User;
import com.example.restaurant.entity.enums.RoleEnum;
import com.example.restaurant.repository.RoleRepository;
import com.example.restaurant.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (roleRepository.count() == 0) {
            roleRepository.saveAll(List.of(
                    new Role(1, RoleEnum.ROLE_USER),
                    new Role(2, RoleEnum.ROLE_ADMIN),
                    new Role(3, RoleEnum.ROLE_WAITER)
            ));
        }

        if (userRepository.count() == 0) {
            Role role_user = roleRepository.getReferenceById(1);
            Role role_admin = roleRepository.getReferenceById(2);
            Role role_waiter = roleRepository.getReferenceById(3);
            userRepository.saveAll(List.of(
                            new User("+998999686653", passwordEncoder.encode("root123"), "Nuriddin", "Inoyatov",
                                    roleRepository.findAll()),
                            new User("+998771234567", passwordEncoder.encode("root123"), "Client", "Clientov",
                                    List.of(role_user)),
                            new User("+998881234567", passwordEncoder.encode("root123"), "Waiter", "Doe",
                                    List.of(role_user, role_waiter))
                    )
            );

        }

    }
}
