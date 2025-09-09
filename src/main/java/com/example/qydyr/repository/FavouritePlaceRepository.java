package com.example.qydyr.repository;

import com.example.qydyr.model.FavoritePlaces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouritePlaceRepository extends JpaRepository<FavoritePlaces,Long> {

}
