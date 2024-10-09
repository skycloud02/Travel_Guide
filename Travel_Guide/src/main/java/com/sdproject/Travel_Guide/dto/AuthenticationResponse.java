package com.sdproject.Travel_Guide.dto;

import com.sdproject.Travel_Guide.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;
    private UserRole userRole;
    private Long userId;

}
