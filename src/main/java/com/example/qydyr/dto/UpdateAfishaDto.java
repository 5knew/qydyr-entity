package com.example.qydyr.dto;

import com.example.qydyr.model.enums.EventCategory;
import com.example.qydyr.model.enums.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateAfishaDto {

    private String name;
    private String addressName;
    private String addressLink;
    private LocalDateTime createdDateTime;
    private LocalDateTime eventDateTimeFrom;
    private LocalDateTime eventDateTimeTo;
    private String phone;
    private String description;
    private Integer price;
    private String street;
    private String city;
    private String country;
    private Status status;
    private Boolean geoProcessed;
    private EventCategory category;

}

