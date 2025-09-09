package com.example.qydyr.service;


import com.example.qydyr.model.GeoLocation;

import java.util.Optional;

public interface GeoLocationService {
    Optional<GeoLocation> computeGeoLocation(String fullAddressLine);
}