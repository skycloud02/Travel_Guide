package com.sdproject.Travel_Guide.services.auth;

import com.sdproject.Travel_Guide.dto.SignupRequest;
import com.sdproject.Travel_Guide.dto.UserDto;

public interface AuthService {

    UserDto createCustomer(SignupRequest signupRequest);

    boolean hasCustomerWithEmail(String email);
}
