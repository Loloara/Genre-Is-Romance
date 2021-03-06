package com.loloara.genreisromance.repository;

import com.loloara.genreisromance.model.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, String> {

    @Query("select p from Place p left join fetch p.matchPlaces")
    List<Place> findFetchAll();

    @Query("select p from Place p join fetch p.matchPlaces where p.placeName = :placeName")
    Optional<Place> findByIdFetchAll(@Param("placeName") String placeName);
}
