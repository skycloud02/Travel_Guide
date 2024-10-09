package com.sdproject.Travel_Guide.controller;

import com.sdproject.Travel_Guide.dto.SearchTripDto;
import com.sdproject.Travel_Guide.dto.TripDto;
import com.sdproject.Travel_Guide.dto.UserDto;
import com.sdproject.Travel_Guide.services.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/trip")
    public ResponseEntity<?> postTrip(@ModelAttribute TripDto tripDto) throws IOException {
        boolean success = adminService.postTrip(tripDto);
        if (success){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/trips")
    public ResponseEntity<?> getAllTrips(){
        return ResponseEntity.ok(adminService.getAllTrips());
    }

    @DeleteMapping("/trip/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id){
        adminService.deleteTrip(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/trip/{id}")
    public ResponseEntity<TripDto> getTripById(@PathVariable Long id){
        TripDto tripDto = adminService.getTripById(id);
        return ResponseEntity.ok(tripDto);
    }

    @PutMapping("/trip/{tripId}")
    public ResponseEntity<Void> updateTrip(@PathVariable Long tripId, @ModelAttribute TripDto tripDto) throws IOException {
        try{
        boolean success = adminService.updateTrip(tripId, tripDto);
        if(success) return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/trip/search")
    public ResponseEntity<?> searchTrip(@RequestBody SearchTripDto searchTripDto){
        return ResponseEntity.ok(adminService.searchTrip(searchTripDto));
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        UserDto userDto = adminService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        adminService.deleteCustomer(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable Long userId, @ModelAttribute UserDto UserDto) throws IOException {
        try{
            boolean success = adminService.updateUser(userId, UserDto);
            if(success) return ResponseEntity.status(HttpStatus.OK).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}