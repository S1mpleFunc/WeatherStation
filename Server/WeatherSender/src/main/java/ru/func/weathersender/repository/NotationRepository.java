package ru.func.weathersender.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.func.weathersender.entity.Notation;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotationRepository extends JpaRepository<Notation, Integer> {

    List<Notation> findNotationsByLocationAndIsPublic(String location, Boolean isPublic);

    List<Notation> findNotationsByTimestampAndIsPublic(String timestamp, Boolean isPublic);

    @Query(value = "SELECT * FROM notations WHERE id = (SELECT MAX(id) FROM notations WHERE location = ?1 AND is_public = ?2)", nativeQuery = true)
    Optional<Notation> findNewestNotationByLocation(String location, Boolean isPublic);

    @Query(value = "SELECT * FROM notations WHERE id = (SELECT MAX(id) FROM notations WHERE author = ?1)", nativeQuery = true)
    Optional<Notation> findNewestByAuthor(String author);
}
