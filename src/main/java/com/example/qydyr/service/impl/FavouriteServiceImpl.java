package com.example.qydyr.service.impl;

import com.example.qydyr.model.*;
import com.example.qydyr.repository.FavouriteAfishesRepository;
import com.example.qydyr.repository.FavouritePlaceRepository;
import com.example.qydyr.service.AfishaService;
import com.example.qydyr.service.FavouriteService;
import com.example.qydyr.service.PlaceService;
import com.example.qydyr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavouriteServiceImpl implements FavouriteService {

    private final FavouriteAfishesRepository favouriteAfishesRepository;
    private final FavouritePlaceRepository favouritePlaceRepository;
    private final UserService userService;
    private final PlaceService placeService;
    private final AfishaService afishaService;

    @Override
    public void saveFavoritePlaces(Long userId, Long placeId) {
        FavoritePlaces favoritePlaces = new FavoritePlaces();
        User user = userService.getUserById(userId);
        Place place = placeService.getById(placeId);
        favoritePlaces.setPlace(place);
        favoritePlaces.setUserId(user);
        favouritePlaceRepository.save(favoritePlaces);
    }

    @Override
    public void saveFavoriteAfishes(Long userId, Long afishaId) {
        FavoriteAfishes favoriteAfishes = new FavoriteAfishes();
        User user = userService.getUserById(userId);
        Afisha afisha = afishaService.getById(afishaId);
        favoriteAfishes.setAfisha(afisha);
        favoriteAfishes.setUserId(user);
        favouriteAfishesRepository.save(favoriteAfishes);
    }
}
