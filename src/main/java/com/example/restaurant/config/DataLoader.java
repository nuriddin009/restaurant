package com.example.restaurant.config;

import com.example.restaurant.entity.*;
import com.example.restaurant.entity.enums.AvailabilityStatus;
import com.example.restaurant.entity.enums.RoleEnum;
import com.example.restaurant.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CategoryRepository categoryRepository;
    private final MenuRepository menuRepository;
    private final RestaurantTableRepository restaurantTableRepository;
    private final AttachmentRepository attachmentRepository;


    @Override
    public void run(String... args) throws Exception {

        if (roleRepository.count() == 0) {
            roleRepository.saveAll(List.of(
                    new Role(1, RoleEnum.ROLE_ADMIN),
                    new Role(2, RoleEnum.ROLE_WAITER)
            ));
        }

        if (userRepository.count() == 0) {
            Role role_admin = roleRepository.getReferenceById(1);
            Role role_waiter = roleRepository.getReferenceById(2);

            userRepository.saveAll(List.of(
                            new User("+998999686653", passwordEncoder.encode("root123"), "Nuriddin", "Inoyatov",
                                    roleRepository.findAll()),
                            new User("+998881234567", passwordEncoder.encode("root123"), "John", "Doe",
                                    List.of(role_waiter)),
                            new User("+998771234567", passwordEncoder.encode("root123"), "Anna", "Smith",
                                    List.of(role_waiter)),
                            new User("+998551234567", passwordEncoder.encode("root123"), "Peter", "Pen",
                                    List.of(role_waiter))
                    )
            );

        }

        if (categoryRepository.count() == 0) {
            Category lavash = categoryRepository.save(new Category("Lavash"));
            Category burger = categoryRepository.save(new Category("Burgerlar"));
            Category milliyTaom = categoryRepository.save(new Category("Milliy taomlar"));
            Category drinks = categoryRepository.save(new Category("Ichimliklar"));

            Attachment lavashImg = attachmentRepository.save(new Attachment(UUID.randomUUID(), "images/lavash.png"));
            Attachment burgerImg = attachmentRepository.save(new Attachment(UUID.randomUUID(), "images/burger.png"));
            Attachment oshImg = attachmentRepository.save(new Attachment(UUID.randomUUID(), "images/osh.png"));
            Attachment mantiImg = attachmentRepository.save(new Attachment(UUID.randomUUID(), "images/manti.png"));
            Attachment shurvaImg = attachmentRepository.save(new Attachment(UUID.randomUUID(), "images/shurva.png"));
            Attachment cocaColaImg = attachmentRepository.save(new Attachment(UUID.randomUUID(), "images/cocacola.png"));
            Attachment choyImg = attachmentRepository.save(new Attachment(UUID.randomUUID(), "images/choy.png"));

            menuRepository.saveAll(List.of(
                    new Menu("Mini lavash", 18000L, lavash, lavashImg),
                    new Menu("Big Lavash", 24000L, lavash, lavashImg),
                    new Menu("Chicken Lavash", 22000L, lavash, lavashImg),
                    new Menu("Cheese Lavash", 23000L, lavash, lavashImg),
                    new Menu("Mini burger", 18000L, burger, burgerImg),
                    new Menu("Big burger", 25000L, burger, burgerImg),
                    new Menu("Mega burger", 28000L, burger, burgerImg),
                    new Menu("Spicy blast burger", 24000L, burger, burgerImg),
                    new Menu("Osh", 30000L, milliyTaom, oshImg),
                    new Menu("Manti", 22000L, milliyTaom, mantiImg),
                    new Menu("Sho'rva", 20000L, milliyTaom, shurvaImg),
                    new Menu("Coca-cola", 10000L, drinks, cocaColaImg),
                    new Menu("Ko'k choy", 6000L, drinks, choyImg)
            ));
        }

        if (restaurantTableRepository.count() == 0) {
            restaurantTableRepository.saveAll(List.of(
                    new RestaurantTable(1, 4, AvailabilityStatus.VACANT),
                    new RestaurantTable(2, 8, AvailabilityStatus.VACANT),
                    new RestaurantTable(3, 10, AvailabilityStatus.VACANT),
                    new RestaurantTable(4, 2, AvailabilityStatus.VACANT),
                    new RestaurantTable(5, 4, AvailabilityStatus.VACANT)
            ));
        }


    }
}
