package com.spring.lab1.genre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/genres")
public class GenreController {

    private com.spring.lab1.genre.service.GenreService genreService;


    @Autowired
    public GenreController(com.spring.lab1.genre.service.GenreService genreService) {
        this.genreService = genreService;
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteGenre(@PathVariable("name") String name) {
        Optional<com.spring.lab1.genre.entity.Genre> genre = genreService.find(name);
        if (genre.isPresent()) {
            genreService.delete(genre.get());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Void> postGenre(@RequestBody com.spring.lab1.genre.dto.PostGenreRequest request, UriComponentsBuilder builder) {
        com.spring.lab1.genre.entity.Genre genre = com.spring.lab1.genre.dto.PostGenreRequest.dtoToEntityMapper().apply(request);
        genreService.create(genre);
        return ResponseEntity
                .created(builder
                        .pathSegment("api", "genres", "{name}")
                        .buildAndExpand(genre.getName()).toUri())
                .build();
    }

}

