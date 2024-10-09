package com.sdproject.Travel_Guide.services.admin;

import com.sdproject.Travel_Guide.dto.SearchTripDto;
import com.sdproject.Travel_Guide.dto.TripDto;
import com.sdproject.Travel_Guide.dto.TripDtoListDto;
import com.sdproject.Travel_Guide.dto.UserDto;

import java.io.IOException;
import java.util.List;

public interface AdminService {
    boolean postTrip(TripDto tripDto) throws IOException;

    List<TripDto> getAllTrips();

    void deleteTrip(Long id);

    void deleteCustomer(Long id);

    TripDto getTripById(Long id);

    boolean updateTrip(Long tripId, TripDto tripDto) throws IOException;

    TripDtoListDto searchTrip(SearchTripDto searchTripDto);
    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    boolean updateUser(Long userId, UserDto userDto) throws IOException;
}
