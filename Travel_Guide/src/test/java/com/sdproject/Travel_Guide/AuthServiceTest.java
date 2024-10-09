package com.sdproject.Travel_Guide;

import com.sdproject.Travel_Guide.dto.SignupRequest;
import com.sdproject.Travel_Guide.dto.UserDto;
import com.sdproject.Travel_Guide.entity.User;
import com.sdproject.Travel_Guide.enums.UserRole;
import com.sdproject.Travel_Guide.repository.UserRepository;
import com.sdproject.Travel_Guide.services.auth.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    public void setUp() {
        // Call the method manually since @PostConstruct methods are not automatically called in tests
        authService.createAdminAccount();
    }

    @Test
    public void testHasCustomerWithEmail_Exists() {
        String email = "customer@test.com";
        User user = new User();
        user.setEmail(email);

        when(userRepository.findFirstByEmail(email)).thenReturn(Optional.of(user));

        boolean result = authService.hasCustomerWithEmail(email);

        assertTrue(result);
        verify(userRepository, times(1)).findFirstByEmail(email);
    }

    @Test
    public void testHasCustomerWithEmail_DoesNotExist() {
        String email = "nonexistent@test.com";

        when(userRepository.findFirstByEmail(email)).thenReturn(Optional.empty());

        boolean result = authService.hasCustomerWithEmail(email);

        assertFalse(result);
        verify(userRepository, times(1)).findFirstByEmail(email);
    }
}
