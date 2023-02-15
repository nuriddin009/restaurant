package com.example.restaurant.config;

import com.example.restaurant.entity.User;
import com.example.restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;


public class AuditorAwareImpl implements AuditorAware<User> {


    @Autowired
    private  UserRepository userRepository;



    @Override
    public Optional<User> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication == null || !authentication.isAuthenticated())) {
            return userRepository.findByPhoneNumber((String) authentication.getPrincipal());
        }
        return Optional.empty();
    }
}
