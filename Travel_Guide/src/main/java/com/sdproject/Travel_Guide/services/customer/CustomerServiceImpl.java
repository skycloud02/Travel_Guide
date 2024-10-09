package com.sdproject.Travel_Guide.services.customer;

import com.sdproject.Travel_Guide.dto.BookATripDto;
import com.sdproject.Travel_Guide.dto.TripDto;
import com.sdproject.Travel_Guide.entity.BookATrip;
import com.sdproject.Travel_Guide.entity.Trip;
import com.sdproject.Travel_Guide.entity.User;
import com.sdproject.Travel_Guide.enums.BookTripStatus;
import com.sdproject.Travel_Guide.repository.BookATripRepository;
import com.sdproject.Travel_Guide.repository.TripRepository;
import com.sdproject.Travel_Guide.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final TripRepository tripRepository;

    private final UserRepository userRepository;

    private final BookATripRepository bookATripRepository;

    @Override
    public List<TripDto> getAllTrips() {
        return tripRepository.findAll().stream().map(Trip::getTripDto).collect(Collectors.toList());
    }

    @Override
    public boolean bookATrip(BookATripDto bookATripDto) {

        Optional<Trip> optionalTrip = tripRepository.findById(bookATripDto.getTripId());
        Optional<User> optionalUser = userRepository.findById(bookATripDto.getUserId());
        if(optionalTrip.isPresent() && optionalUser.isPresent()){
            Trip existingTrip = optionalTrip.get();
            BookATrip bookATrip = new BookATrip();
            bookATrip.setUser(optionalUser.get());
            bookATrip.setTrip(existingTrip);
            bookATrip.setBookTripStatus(BookTripStatus.PENDING);
            long diffInMilliSeconds = bookATripDto.getToDate().getTime() - bookATripDto.getFromDate().getTime();
            long days = TimeUnit.MICROSECONDS.toDays(diffInMilliSeconds);
            bookATrip.setDays(days);
            bookATrip.setPrice(existingTrip.getPrice());
            bookATripRepository.save(bookATrip);
            return true;
        }
        return false;
    }

    @Override
    public TripDto getTripById(Long tripId) {
        Optional<Trip> optionalTrip = tripRepository.findById(tripId);
        return optionalTrip.map(Trip::getTripDto).orElse(null);
    }

    @Override
    public List<BookATripDto> getBookingsByUserId(Long userId) {
        return bookATripRepository.findAllByUserId(userId).stream().map(BookATrip::getBookATripDto).collect(Collectors.toList());
    }
}
