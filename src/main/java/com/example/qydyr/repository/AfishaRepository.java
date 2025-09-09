package com.example.qydyr.repository;

import com.example.qydyr.model.Afisha;
import com.example.qydyr.model.enums.EventCategory;
import com.example.qydyr.model.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AfishaRepository extends JpaRepository<Afisha, Long> {

    @Query(value = "select t from Afisha t " +
            "where t.category = case when :category is not null then :category else t.category end " +
            " and t.status = case when :status is not null then :status else t.status end")
    List<Afisha> findAfishesByStatusAndCategory(@Param("category") EventCategory category,
                                             @Param("status") Status status);

    @Query(value = "select t from Afisha t " +
            "where (:search is null or :search = '' or lower(t.name) like lower(concat('%', :search, '%'))) " +
            "and (:category is null or t.category = :category) " +
            "and (:status is null or t.status = :status)")
    List<Afisha> findAfishesBySearchAndFilter(@Param("search") String search,
                                            @Param("category") EventCategory category,
                                            @Param("status") Status status);

    @Query(value = "select t from Afisha t " +
            "where (:search is null or :search = '' or lower(t.name) like lower(concat('%', :search, '%'))) " +
            "and (:category is null or t.category = :category) " +
            "and (:status is null or t.status = :status)")
    Page<Afisha> findAfishesBySearchAndFilter(@Param("search") String search,
                                            @Param("category") EventCategory category,
                                            @Param("status") Status status,
                                            Pageable pageable);

    @Query(value = "SELECT i.* FROM afisha i JOIN favorite_afishes c ON i.id = c.id WHERE c.user_id = ?1",nativeQuery = true)
    List<Afisha> findAllAfishesByUserIdAndLikeId(Long id);

    Optional<Afisha> findByName(String name);
}
