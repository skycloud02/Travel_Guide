package com.sdproject.Travel_Guide.entity;

import com.sdproject.Travel_Guide.dto.TripDto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "trips")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;

    private String destination;

    private String hotelName;

    private Long price;

    private String startDate;

    private String endDate;

    private String description;

    @Column(columnDefinition = "longblob")
    private byte[] image;

    public TripDto getTripDto(){
        TripDto tripDto = new TripDto();
        tripDto.setId(id);
        tripDto.setCountry(country);
        tripDto.setDestination(destination);
        tripDto.setHotelName(hotelName);
        tripDto.setPrice(price);
        tripDto.setStartDate(startDate);
        tripDto.setEndDate(endDate);
        tripDto.setDescription(description);
        tripDto.setReturnedImage(image);
        return tripDto;
    }
}
