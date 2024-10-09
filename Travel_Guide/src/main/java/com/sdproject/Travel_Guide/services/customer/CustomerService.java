package com.sdproject.Travel_Guide.services.customer;

import com.sdproject.Travel_Guide.dto.BookATripDto;
import com.sdproject.Travel_Guide.dto.TripDto;

import java.util.List;

public interface CustomerService {
    List<TripDto> getAllTrips();

    boolean bookATrip(BookATripDto bookATripDto);

    TripDto getTripById(Long tripId);

    List<BookATripDto> getBookingsByUserId(Long userId);
}
