package com.example.qydyr.dto;

import com.example.qydyr.model.enums.Category;
import com.example.qydyr.model.enums.Status;
import lombok.Data;

@Data
public class CreatePlaceDto {

    private String name;
    private String streetName;
    private String streetNumber;
    private String placeLink;
    private String description;
    private Integer priceFrom;
    private Integer priceTo;
    private String socialNetwork;
    private String phone;
    private String email;
    private Integer capacity;
    private String street;
    private String city;
    private String country;
    private Boolean geoProcessed;

    private Status status;
    private Category category;

}