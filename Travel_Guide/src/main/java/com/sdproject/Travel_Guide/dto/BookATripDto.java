package com.sdproject.Travel_Guide.dto;

import com.sdproject.Travel_Guide.enums.BookTripStatus;
import lombok.Data;

import java.util.Date;

@Data
public class BookATripDto {
    private Long id;

    private Date fromDate;

    private Date toDate;

    private Long days;

    private Long price;

    private BookTripStatus bookTripStatus;

    private Long tripId;

    private Long userId;

    private String username;

    private String email;

}
