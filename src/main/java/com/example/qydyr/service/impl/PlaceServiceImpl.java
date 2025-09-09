package com.example.qydyr.service.impl;

import com.example.qydyr.dto.CreatePlaceDto;
import com.example.qydyr.dto.UpdatePlaceDto;
import com.example.qydyr.model.Address;
import com.example.qydyr.model.Image;
import com.example.qydyr.model.Place;
import com.example.qydyr.repository.PlaceRepository;
import com.example.qydyr.service.GeoLocationService;
import com.example.qydyr.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository placeRepository;
    private final GeoLocationService geoLocationService;
    @Transactional
    public void create(CreatePlaceDto form) {
        final Place place = new Place(form.getName(), form.getStreetName(), form.getStreetNumber(),
                                    form.getPlaceLink(), form.getDescription(), form.getPriceFrom(),
                                    form.getPriceTo(), form.getSocialNetwork(), form.getPhone(),
                                    form.getEmail(), form.getCapacity(),
                                    new Address(form.getStreet(), form.getCity(), form.getCountry()),
                                    form.getStatus(), form.getCategory(), form.getGeoProcessed(), false);
        this.computeAddress(place);
        placeRepository.save(place);
    }

    @Override
    public Optional<Place> getPlaceById(Long id) {
        return placeRepository.findById(id);
    }
    @Override
    @Transactional
    public List<Place> getAll() {
        final List<Place> places = placeRepository.findAll();
        places.forEach(this::computeAddress);
        return places;
    }

    @Override
    public List<Place> getAllLikes(Long id){
        final List<Place> places = placeRepository.findAllPlaceByUserIdAndLikeId(id);
        places.forEach(this::computeAddress);
        return places;
    }
    private void computeAddress(Place place) {
        if (place.getAddress() != null && place.getGeoLocation() == null && !place.isGeoProcessed()) {
            geoLocationService.computeGeoLocation(place.getAddress().toString())
                    .ifPresent(place::setGeoLocation);
            place.setGeoProcessed(true);
            placeRepository.save(place);
        }
    }

    @Override
    @Transactional
    public List<Place> getAllByFilter(CreatePlaceDto placeForm) {
        final List<Place> places = placeRepository.findPlaceByStatusAndCategory(placeForm.getCategory(),placeForm.getStatus());
        places.forEach(this::computeAddress);
        return places;
    }

    @Override
    public Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    @Override
    public Place getById(Long id) {
        return placeRepository.findById(id).orElseThrow();
    }
    @Transactional
    public Place updatePlace(Long id, UpdatePlaceDto form) {
        Optional<Place> optionalPlace = placeRepository.findById(id);
        if (optionalPlace.isPresent()) {
            Place place = optionalPlace.get();
            place.setName(form.getName());
            place.setStreetName(form.getStreetName());
            place.setStreetNumber(form.getStreetNumber());
            place.setPlaceLink(form.getPlaceLink());
            place.setDescription(form.getDescription());
            place.setPriceFrom(form.getPriceFrom());
            place.setPriceTo(form.getPriceTo());
            place.setSocialNetwork(form.getSocialNetwork());
            place.setPhone(form.getPhone());
            place.setEmail(form.getEmail());
            place.setCapacity(form.getCapacity());
            place.setAddress(new Address(form.getStreet(), form.getCity(), form.getCountry()));
            place.setStatus(form.getStatus());
            place.setCategory(form.getCategory());
            place.setGeoProcessed(form.getGeoProcessed());
            this.computeAddress(place);
            return placeRepository.save(place);
        } else {
            throw new IllegalArgumentException("Place with id " + id + " does not exist");
        }
    }
    @Transactional
    public void deletePlace(Long id) {
        placeRepository.deleteById(id);
    }

    @Override
    public Place savePlace(Place place) {
        return placeRepository.save(place);
    }

    @Override
    public long count() {
        return placeRepository.count();
    }

    @Override
    public Page<Place> getAllPlaces(org.springframework.data.domain.Pageable pageable) {
        return placeRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public List<Place> getPlacesBySearchAndFilter(String search, String city, com.example.qydyr.model.enums.Category category) {
        final List<Place> places = placeRepository.findPlacesBySearchAndFilter(search, city, category);
        places.forEach(this::computeAddress);
        return places;
    }

}
