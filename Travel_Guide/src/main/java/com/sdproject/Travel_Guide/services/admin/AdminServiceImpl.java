package com.sdproject.Travel_Guide.services.admin;

import com.sdproject.Travel_Guide.dto.SearchTripDto;
import com.sdproject.Travel_Guide.dto.TripDto;
import com.sdproject.Travel_Guide.dto.TripDtoListDto;
import com.sdproject.Travel_Guide.dto.UserDto;
import com.sdproject.Travel_Guide.entity.Trip;
import com.sdproject.Travel_Guide.entity.User;
import com.sdproject.Travel_Guide.repository.TripRepository;
import com.sdproject.Travel_Guide.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final TripRepository tripRepository;
    private final UserRepository userRepository;

    @Override
    public boolean postTrip(TripDto tripDto) throws IOException {
        try {
            Trip trip = new Trip();
            trip.setCountry(tripDto.getCountry());
            trip.setDestination(tripDto.getDestination());
            trip.setHotelName(tripDto.getHotelName());
            trip.setPrice(tripDto.getPrice());
            trip.setStartDate(tripDto.getStartDate());
            trip.setEndDate(tripDto.getEndDate());
            trip.setDescription(tripDto.getDescription());
            trip.setImage(tripDto.getImage().getBytes());
            tripRepository.save(trip);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<TripDto> getAllTrips() {
        return tripRepository.findAll().stream().map(Trip::getTripDto).collect(Collectors.toList());
    }

    @Override
    public void deleteTrip(Long id) {
        tripRepository.deleteById(id);
    }

    @Override
    public TripDto getTripById(Long id) {
        Optional<Trip> optionalTrip = tripRepository.findById(id);
        return optionalTrip.map(Trip::getTripDto).orElse(null);
    }

    @Override
    public boolean updateTrip(Long tripId, TripDto tripDto) throws IOException {
        Optional<Trip> optionalTrip = tripRepository.findById(tripId);
        if(optionalTrip.isPresent()){
            Trip existingTrip = optionalTrip.get();
            if(tripDto.getImage() != null)
                existingTrip.setImage(tripDto.getImage().getBytes());
            existingTrip.setPrice(tripDto.getPrice());
            existingTrip.setCountry(tripDto.getCountry());
            existingTrip.setDestination(tripDto.getDestination());
            existingTrip.setHotelName(tripDto.getHotelName());
            existingTrip.setStartDate(tripDto.getStartDate());
            existingTrip.setEndDate(tripDto.getEndDate());
            existingTrip.setDescription(tripDto.getDescription());
            tripRepository.save(existingTrip);
            return true;
            } else {
            return false;
        }
    }

    @Override
    public TripDtoListDto searchTrip(SearchTripDto searchTripDto) {
        Trip trip = new Trip();
        trip.setCountry(searchTripDto.getCountry());
        ExampleMatcher exampleMatcher =
                ExampleMatcher.matchingAll()
                        .withMatcher("country", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        Example<Trip> tripExample = Example.of(trip, exampleMatcher);
        List<Trip> tripList = tripRepository.findAll(tripExample);
        TripDtoListDto tripDtoListDto = new TripDtoListDto();
        tripDtoListDto.setTripDtoList(tripList.stream().map(Trip::getTripDto).collect(Collectors.toList()));
        return tripDtoListDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(User::getUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(User::getUserDto).orElse(null);
    }

    @Override
    public boolean updateUser(Long userId, UserDto userDto) throws IOException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            User existingUser = optionalUser.get();
            existingUser.setName(userDto.getName());
            existingUser.setEmail(userDto.getEmail());
            userRepository.save(existingUser);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void deleteCustomer(Long id) {
        userRepository.deleteById(id);
    }
}
