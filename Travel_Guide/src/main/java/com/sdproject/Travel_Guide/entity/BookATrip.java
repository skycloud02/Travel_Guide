package com.sdproject.Travel_Guide.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdproject.Travel_Guide.dto.BookATripDto;
import com.sdproject.Travel_Guide.enums.BookTripStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
public class BookATrip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fromDate;

    private Date toDate;

    private Long days;

    private Long price;

    private BookTripStatus bookTripStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trip_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Trip trip;

    public BookATripDto getBookATripDto(){
        BookATripDto bookATripDto = new BookATripDto();
        bookATripDto.setId(id);
        bookATripDto.setDays(days);
        bookATripDto.setBookTripStatus(bookTripStatus);
        bookATripDto.setPrice(price);
        bookATripDto.setFromDate(fromDate);
        bookATripDto.setToDate(toDate);
        bookATripDto.setEmail(user.getEmail());
        bookATripDto.setUsername(user.getName());
        bookATripDto.setUserId(user.getId());
        bookATripDto.setTripId(trip.getId());
        return bookATripDto;
    }

}
