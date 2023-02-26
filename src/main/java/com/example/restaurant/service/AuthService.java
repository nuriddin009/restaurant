package com.example.restaurant.service;

import com.example.restaurant.dto.request.AuthenticationRequest;
import com.example.restaurant.dto.request.RegisterRequest;
import com.example.restaurant.dto.response.AuthenticationResponse;
import com.example.restaurant.entity.User;
import com.example.restaurant.entity.enums.RoleEnum;
import com.example.restaurant.repository.RoleRepository;
import com.example.restaurant.repository.UserRepository;
import com.example.restaurant.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;


//    public AuthenticationResponse register(RegisterRequest request) {
//        Optional<User> byPhoneNumber = userRepository.findByPhoneNumber(request.getPhoneNumber());
//        if (byPhoneNumber.isEmpty()) {
//            try {
//                User user = User.builder()
//                        .firstName(request.getFirstName())
//                        .lastName(request.getFirstName())
//                        .phoneNumber(request.getPhoneNumber())
//                        .password(passwordEncoder.encode(request.getPassword()))
//                        .roles(List.of(roleRepository.getReferenceById(1)))
//                        .build();
//                userRepository.save(user);
//
//                Map<String, Object> claims = new HashMap<>();
//                claims.put(RoleEnum.ROLE_USER.name(), RoleEnum.ROLE_USER.name());
//
//                String token = jwtService.generateToken(claims, user);
//
//                return AuthenticationResponse
//                        .builder()
//                        .accessToken(token)
//                        .success(true)
//                        .message("You have successfully registered")
//                        .build();
//            } catch (Exception e) {
//                return new AuthenticationResponse(false, "The phone number or password is incorrect");
//            }
//        } else {
//            return new AuthenticationResponse(false, "This number is registered");
//        }
//    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getPhoneNumber(), request.getPassword())
            );
            User user = userRepository.findByPhoneNumber(request.getPhoneNumber()).orElseThrow(
                    () -> new UsernameNotFoundException("User not found"));
            Map<String, Object> collect = user.getRoles().stream()
                    .collect(Collectors.toMap(i -> i.getRoleName().toString(), i -> i.getRoleName().toString()));
            String jwtToken = jwtService.generateToken(collect, user);

            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .success(true)
                    .message("You have successfully login")
                    .build();
        } catch (Exception e) {
            return new AuthenticationResponse(false, "The phone number or password is incorrect");
        }

    }
}
