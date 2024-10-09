package com.sdproject.Travel_Guide;

import com.sdproject.Travel_Guide.dto.TripDto;
import com.sdproject.Travel_Guide.dto.UserDto;
import com.sdproject.Travel_Guide.entity.Trip;
import com.sdproject.Travel_Guide.entity.User;
import com.sdproject.Travel_Guide.repository.TripRepository;
import com.sdproject.Travel_Guide.repository.UserRepository;
import com.sdproject.Travel_Guide.services.admin.AdminServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @Mock
    private TripRepository tripRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AdminServiceImpl adminService;

    @Test
    public void testPostTrip() throws IOException {
        TripDto tripDto = new TripDto();
        tripDto.setCountry("Country");
        tripDto.setDestination("Destination");
        tripDto.setHotelName("Hotel");
        tripDto.setPrice(1000L);
        tripDto.setStartDate("2024-06-01");
        tripDto.setEndDate("2024-06-10");
        tripDto.setDescription("Description");
        tripDto.setImage(new MockMultipartFile("image", "image.jpg", "image/jpeg", "test image content".getBytes()));

        Trip trip = new Trip();
        when(tripRepository.save(any(Trip.class))).thenReturn(trip);

        boolean result = adminService.postTrip(tripDto);

        assertTrue(result);
        verify(tripRepository, times(1)).save(any(Trip.class));
    }

    @Test
    public void testGetAllTrips() {
        Trip trip1 = new Trip();
        trip1.setCountry("Country1");
        Trip trip2 = new Trip();
        trip2.setCountry("Country2");

        when(tripRepository.findAll()).thenReturn(Arrays.asList(trip1, trip2));

        List<TripDto> trips = adminService.getAllTrips();

        assertEquals(2, trips.size());
        verify(tripRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteTrip() {
        Long tripId = 1L;
        doNothing().when(tripRepository).deleteById(tripId);

        adminService.deleteTrip(tripId);

        verify(tripRepository, times(1)).deleteById(tripId);
    }

    @Test
    public void testGetTripById() {
        Long tripId = 1L;
        Trip trip = new Trip();
        when(tripRepository.findById(tripId)).thenReturn(Optional.of(trip));

        TripDto tripDto = adminService.getTripById(tripId);

        assertNotNull(tripDto);
        verify(tripRepository, times(1)).findById(tripId);
    }

    @Test
    public void testUpdateTrip() throws IOException {
        Long tripId = 1L;
        TripDto tripDto = new TripDto();
        tripDto.setCountry("New Country");

        Trip trip = new Trip();
        when(tripRepository.findById(tripId)).thenReturn(Optional.of(trip));
        when(tripRepository.save(any(Trip.class))).thenReturn(trip);

        boolean result = adminService.updateTrip(tripId, tripDto);

        assertTrue(result);
        verify(tripRepository, times(1)).findById(tripId);
        verify(tripRepository, times(1)).save(any(Trip.class));
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setName("User1");
        User user2 = new User();
        user2.setName("User2");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<UserDto> users = adminService.getAllUsers();

        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetUserById() {
        Long userId = 1L;
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserDto userDto = adminService.getUserById(userId);

        assertNotNull(userDto);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void testUpdateUser() throws IOException {
        Long userId = 1L;
        UserDto userDto = new UserDto();
        userDto.setName("New Name");

        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        boolean result = adminService.updateUser(userId, userDto);

        assertTrue(result);
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testDeleteCustomer() {
        Long userId = 1L;
        doNothing().when(userRepository).deleteById(userId);

        adminService.deleteCustomer(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }
}
