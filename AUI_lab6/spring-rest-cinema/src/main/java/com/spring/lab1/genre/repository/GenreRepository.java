package com.spring.lab1.genre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface GenreRepository extends JpaRepository<com.spring.lab1.genre.entity.Genre, String> {

}
