package com.spring.lab1.genre.repository;

import com.spring.lab1.genre.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface GenreRepository extends JpaRepository<Genre, String> {

}
