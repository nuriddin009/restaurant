package com.example.restaurant.service;

import com.example.restaurant.dto.request.RegisterRequest;
import com.example.restaurant.dto.response.ApiResponse;
import com.example.restaurant.dto.response.BaseResponse;
import com.example.restaurant.dto.response.UserDto;
import com.example.restaurant.entity.Role;
import com.example.restaurant.entity.User;
import com.example.restaurant.repository.RoleRepository;
import com.example.restaurant.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserSession userSession;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    public ApiResponse getMe() {
        ApiResponse apiResponse = new ApiResponse();
        try {
            User user = userSession.getUser();
            apiResponse.setSuccess(true);
            apiResponse.setMessage(user.getFirstName() + " " + user.getLastName());
            apiResponse.setData(new UserDto(user.getPhoneNumber(), user.getRoles()));
        } catch (Exception e) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }

    public BaseResponse<ApiResponse> registerWaiter(RegisterRequest request, BaseResponse<ApiResponse> response) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            Optional<User> byPhoneNumber = userRepository.findByPhoneNumber(request.getPhoneNumber());
            if (byPhoneNumber.isEmpty()) {
                Role role_waiter = roleRepository.getReferenceById(2);
                User user = userRepository.save(new User(
                        request.getPhoneNumber(),
                        passwordEncoder.encode(request.getPassword()),
                        request.getFirstName(),
                        request.getLastName(),
                        List.of(role_waiter)
                ));
                apiResponse.setMessage(user.getFirstName() + " " + user.getLastName() + " registered");
                apiResponse.setData(user);
                apiResponse.setSuccess(true);
            } else {
                apiResponse.setSuccess(false);
                response.setError(true);
                response.setMessage(request.getPhoneNumber() + " this number is already registered");
            }
        } catch (Exception e) {
            response.setError(true);
            response.setMessage(e.getMessage());
        }
        response.setResponseData(apiResponse);
        return response;
    }
}
