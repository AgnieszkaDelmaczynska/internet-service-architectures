package com.spring.lab1.genre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private com.spring.lab1.genre.repository.GenreRepository repository;


    @Autowired
    public GenreService(com.spring.lab1.genre.repository.GenreRepository repository) {
        this.repository = repository;
    }

    public Optional<com.spring.lab1.genre.entity.Genre> find(String name) {
        return repository.findById(name);
    }

    public List<com.spring.lab1.genre.entity.Genre> findAll() {
        return repository.findAll();
    }

    @Transactional
    public com.spring.lab1.genre.entity.Genre create(com.spring.lab1.genre.entity.Genre genre) {
        return repository.save(genre);
    }

    @Transactional
    public void update(com.spring.lab1.genre.entity.Genre genre) {
        repository.save(genre);
    }

    @Transactional
    public void delete(com.spring.lab1.genre.entity.Genre genre) {
        repository.delete(genre);
    }
}
