package com.sdproject.Travel_Guide.dto;

import com.sdproject.Travel_Guide.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long id;

    private String name;

    private String email;

    private UserRole userRole;
}
