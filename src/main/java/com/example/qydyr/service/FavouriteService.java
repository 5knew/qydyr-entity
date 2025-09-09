package com.example.qydyr.service;

public interface FavouriteService {

    void saveFavoritePlaces(Long userId, Long placeId);

    void saveFavoriteAfishes(Long userId, Long afishaId);
}
