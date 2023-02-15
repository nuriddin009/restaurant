package com.example.restaurant.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {

    private Boolean success;

    private String accessToken;

    private String message;

    public AuthenticationResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
