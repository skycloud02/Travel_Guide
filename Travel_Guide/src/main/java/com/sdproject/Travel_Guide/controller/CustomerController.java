package com.sdproject.Travel_Guide.controller;

import com.sdproject.Travel_Guide.dto.BookATripDto;
import com.sdproject.Travel_Guide.dto.TripDto;
import com.sdproject.Travel_Guide.entity.Trip;
import com.sdproject.Travel_Guide.services.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/trips")
    public ResponseEntity<List<TripDto>> getAllTrips(){
        List<TripDto> tripDtoList = customerService.getAllTrips();
        return ResponseEntity.ok(tripDtoList);
    }

    @PostMapping("/trip/book")
    public ResponseEntity<Void> bookATrip(@RequestBody BookATripDto bookATripDto){
        boolean success = customerService.bookATrip(bookATripDto);
        if(success) return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/trip/{tripId}")
    public ResponseEntity<TripDto> getTripById(@PathVariable Long tripId){
        TripDto tripDto = customerService.getTripById(tripId);
        if(tripDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(tripDto);
    }

    @GetMapping("/trip/bookings/{userId}")
    public ResponseEntity<List<BookATripDto>> getBookingsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(customerService.getBookingsByUserId(userId));
    }
}
