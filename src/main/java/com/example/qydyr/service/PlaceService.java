package com.example.qydyr.service;

import com.example.qydyr.dto.CreatePlaceDto;
import com.example.qydyr.dto.UpdatePlaceDto;
import com.example.qydyr.model.Image;
import com.example.qydyr.model.Place;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public interface PlaceService {
    void create(CreatePlaceDto form);
    List<Place> getAll();

    Optional<Place> getPlaceById(Long id);

    List<Place> getAllLikes(Long id);


    List<Place> getAllByFilter(CreatePlaceDto placeForm);

    Image toImageEntity(MultipartFile file) throws IOException;

    Place getById(Long id);

    Place updatePlace(Long id, UpdatePlaceDto form);

    void deletePlace(Long id);
    
    Place savePlace(Place place);
    long count();
    Page<Place> getAllPlaces(org.springframework.data.domain.Pageable pageable);
    List<Place> getPlacesBySearchAndFilter(String search, String city, com.example.qydyr.model.enums.Category category);
}

