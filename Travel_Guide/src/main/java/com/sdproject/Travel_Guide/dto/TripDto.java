package com.sdproject.Travel_Guide.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TripDto {
    private Long id;

    private String country;

    private String destination;

    private String hotelName;

    private Long price;

    private String startDate;

    private String endDate;

    private String description;

    private MultipartFile image;

    private byte[] returnedImage;
}
