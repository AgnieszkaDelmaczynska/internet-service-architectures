package com.spring.lab1.cinema.repository;

import com.spring.lab1.cinema.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    List<Cinema> findAllByGenre(com.spring.lab1.genre.entity.Genre genre);

    Optional<Cinema> findByIdAndGenre(Long id, com.spring.lab1.genre.entity.Genre genre);

}
