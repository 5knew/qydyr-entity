package com.example.qydyr.repository;

import com.example.qydyr.model.FavoriteAfishes;
import com.example.qydyr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteAfishesRepository extends JpaRepository<FavoriteAfishes,Long> {

    FavoriteAfishes findAllByUserId(User userId);

}
