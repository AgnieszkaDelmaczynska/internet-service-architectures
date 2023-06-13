package com.spring.lab1.genre.service;

import com.spring.lab1.genre.entity.Genre;
import com.spring.lab1.genre.event.repository.GenreEventRepository;
import com.spring.lab1.genre.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private GenreRepository genreRepository;

    // Repository for sending events about actions on genre entities.
    private GenreEventRepository genreEventRepository;


    @Autowired
    public GenreService(GenreRepository genreRepository, GenreEventRepository genreEventRepository) {
        this.genreRepository = genreRepository;
        this.genreEventRepository = genreEventRepository;
    }

    public Optional<Genre> find(String name) {
        return genreRepository.findById(name);
    }

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Transactional
    public void create(Genre genre) {
        genreRepository.save(genre);
        genreEventRepository.create(genre);
    }

    @Transactional
    public void update(Genre genre) {
        genreRepository.save(genre);
    }

    @Transactional
    public void delete(Genre genre) {
        genreRepository.delete(genre);
        genreEventRepository.delete(genre);
    }
}
