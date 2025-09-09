package com.example.qydyr.controller;

import com.example.qydyr.model.FavoriteAfishes;
import com.example.qydyr.model.FavoritePlaces;
import com.example.qydyr.model.Place;
import com.example.qydyr.repository.FavouriteAfishesRepository;
import com.example.qydyr.repository.FavouritePlaceRepository;
import com.example.qydyr.service.FavouriteService;
import com.google.maps.errors.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favourite")
public class FavouriteController {

    private final FavouriteService favouriteService;
    private final FavouritePlaceRepository favouritePlaceRepository;
    private final FavouriteAfishesRepository favouriteAfishesRepository;

    @GetMapping("/places")
    public List<FavoritePlaces> getAllFavoritePlaces() {
        return favouritePlaceRepository.findAll();
    }

    @GetMapping("/afishes")
    public List<FavoriteAfishes> getAllFavoriteAfishes() {
        return favouriteAfishesRepository.findAll();
    }

    @GetMapping("/place/{id}")
    public FavoritePlaces getFavoritePlacesById(@PathVariable("id") Long id) throws NotFoundException {
        return favouritePlaceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Favorite place not found with id: " + id));
    }

    @GetMapping("/afisha/{id}")
    public FavoriteAfishes getFavoriteAfishesById(@PathVariable("id") Long id) throws NotFoundException {
        return favouriteAfishesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Favorite afisha not found with id: " + id));
    }

    @PostMapping("/afisha-add")
    public void saveFavoriteAfishes(@RequestParam("userId") Long userId,
                                               @RequestParam(name = "afishaId") Long afishaId) {
         favouriteService.saveFavoriteAfishes(userId, afishaId);
    }

    @PostMapping("/place-add")
    public void saveFavoritePlaces(@RequestParam("userId") Long userId,
                                             @RequestParam(name = "placeId") Long placeId) {
         favouriteService.saveFavoritePlaces(userId, placeId);
    }
}
