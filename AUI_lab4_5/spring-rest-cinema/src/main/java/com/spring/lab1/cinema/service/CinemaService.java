package com.spring.lab1.cinema.service;

import com.spring.lab1.cinema.entity.Cinema;
import com.spring.lab1.cinema.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CinemaService {

    private CinemaRepository cinemaRepository;
    private com.spring.lab1.genre.repository.GenreRepository genreRepository;

    @Autowired
    public CinemaService(CinemaRepository cinemaRepository, com.spring.lab1.genre.repository.GenreRepository genreRepository) {
        this.cinemaRepository = cinemaRepository;
        this.genreRepository = genreRepository;
    }

    public Optional<Cinema> find(Long id) {
        return cinemaRepository.findById(id);
    }

    public Optional<Cinema> find(String genreName, Long id) {
        Optional<com.spring.lab1.genre.entity.Genre> genre = genreRepository.findById(genreName);
        if (genre.isPresent()) {
            return cinemaRepository.findByIdAndGenre(id, genre.get());
        }
        else {
            return Optional.empty();
        }
    }

    public List<Cinema> findAll(com.spring.lab1.genre.entity.Genre genre) {
        return cinemaRepository.findAllByGenre(genre);
    }

    public List<Cinema> findAll() {
        return cinemaRepository.findAll();
    }

    @Transactional
    public Cinema create(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

    @Transactional
    public void update(Cinema cinema) {
        cinemaRepository.save(cinema);
    }

    @Transactional
    public void delete(Long cinema) {
        cinemaRepository.deleteById(cinema);
    }
}
