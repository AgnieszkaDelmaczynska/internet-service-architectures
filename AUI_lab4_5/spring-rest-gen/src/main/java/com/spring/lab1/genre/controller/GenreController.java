package com.spring.lab1.genre.controller;

import com.spring.lab1.genre.dto.*;
import com.spring.lab1.genre.entity.Genre;
import com.spring.lab1.genre.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/genres")
public class GenreController {

    private GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<GetGenresResponse> getGenres() {
        return ResponseEntity
                .ok(GetGenresResponse.entityToDtoMapper().apply(genreService.findAll()));
    }

    @GetMapping("{name}")
    public ResponseEntity<GetGenreResponse> getGenre(@PathVariable("name") String name) {
        return genreService.find(name)
                .map(value -> ResponseEntity
                        .ok(GetGenreResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());
    }

    @PostMapping
    public ResponseEntity<Void> postGenre(@RequestBody PostGenreRequest request, UriComponentsBuilder builder) {
        Genre genre = PostGenreRequest
                .dtoToEntityMapper()
                .apply(request);
        if (genreService.find(genre.getName()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .build();
        } else {
            genreService.create(genre);
            return ResponseEntity
                    .created(builder
                            .pathSegment("api", "genres", "{name}")
                            .buildAndExpand(genre.getName()).toUri())
                    .build();
        }
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteGenre(@PathVariable("name") String name) {
        Optional<Genre> genre = genreService.find(name);
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

    @PutMapping("{name}")
    public ResponseEntity<Void> putGenre(@RequestBody PutGenreRequest request, @PathVariable("name") String name) {
        Optional<Genre> genre = genreService.find(name);
        if (genre.isPresent()) {
            PutGenreRequest.dtoToEntityUpdater().apply(genre.get(), request);
            genreService.update(genre.get());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}
