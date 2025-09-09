package com.example.qydyr.controller;

import com.example.qydyr.dto.CreatePlaceDto;
import com.example.qydyr.dto.UpdatePlaceDto;
import com.example.qydyr.model.Place;
import com.example.qydyr.service.PlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/places")
public class PlaceController {
    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping
    public ResponseEntity<?> createPlace(@RequestBody CreatePlaceDto form) {
            placeService.create(form);
            return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Place>> getAllPlaces() {
        List<Place> places = placeService.getAll();
        return ResponseEntity.ok(places);
    }

    @GetMapping("/likes/{id}")
    public ResponseEntity<List<Place>> getPlacesByUserIdAndLikeId(@PathVariable Long id) {
        List<Place> places = placeService.getAllLikes(id);
        return ResponseEntity.ok(places);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Place>> getPlacesByFilter(CreatePlaceDto placeForm) {
        List<Place> places = placeService.getAllByFilter(placeForm);
        return ResponseEntity.ok(places);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Place> getPlaceById(@PathVariable Long id) {
        Place place = placeService.getById(id);
        return ResponseEntity.ok(place);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<Place> updatePlace(@PathVariable Long id, @RequestBody UpdatePlaceDto updatePlaceDto) {
        try {
            Place updatedPlace = placeService.updatePlace(id, updatePlaceDto);
            return new ResponseEntity<>(updatedPlace, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deletePlace(@PathVariable Long id) {
        Optional<Place> place = placeService.getPlaceById(id);
        if (place.isPresent()) {
            placeService.deletePlace(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

