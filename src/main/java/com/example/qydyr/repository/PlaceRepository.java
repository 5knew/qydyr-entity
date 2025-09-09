package com.example.qydyr.repository;

import  com.example.qydyr.model.enums.Category;
import com.example.qydyr.model.Place;
import com.example.qydyr.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query(value = "select t from Place t " +
            "where t.category = case when :category is not null then :category else t.category end " +
            " and t.status = case when :status is not null then :status else t.status end")
    List<Place> findPlaceByStatusAndCategory(@Param("category") Category category,
                                             @Param("status") Status status);

    @Query(value = "SELECT i.* FROM place i JOIN favorite_places c ON i.id = c.place_id WHERE c.user_id = ?1",nativeQuery = true)
    List<Place> findAllPlaceByUserIdAndLikeId(Long id);

    Optional<Place> findByName(String name);

    @Query(value = "select p from Place p " +
            "where (:search is null or :search = '' or lower(p.name) like lower(concat('%', :search, '%'))) " +
            "and (:city is null or :city = '' or lower(p.address.city) like lower(concat('%', :city, '%'))) " +
            "and (:category is null or p.category = :category) " +
            "and p.published = true")
    List<Place> findPlacesBySearchAndFilter(@Param("search") String search,
                                           @Param("city") String city,
                                           @Param("category") Category category);

}
