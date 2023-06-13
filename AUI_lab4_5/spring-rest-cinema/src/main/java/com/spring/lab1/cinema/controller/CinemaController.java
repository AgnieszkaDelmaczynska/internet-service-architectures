package com.spring.lab1.cinema.controller;

import com.spring.lab1.cinema.dto.GetCinemaResponse;
import com.spring.lab1.cinema.dto.GetCinemasResponse;
import com.spring.lab1.cinema.dto.PostCinemaRequest;
import com.spring.lab1.cinema.dto.PutCinemaRequest;
import com.spring.lab1.cinema.entity.Cinema;
import com.spring.lab1.cinema.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/cinemas")
public class CinemaController {
    
    private CinemaService cinemaService;
    private com.spring.lab1.genre.service.GenreService genreService;
    
    @Autowired
    public CinemaController(CinemaService cinemaService, com.spring.lab1.genre.service.GenreService genreService) {
        this.cinemaService = cinemaService;
        this.genreService = genreService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})//When empty JSON is default
    public ResponseEntity<GetCinemasResponse> getCinemas() {
        return ResponseEntity
                .ok(GetCinemasResponse.entityToDtoMapper().apply(cinemaService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetCinemaResponse> getCinema(@PathVariable("id") long id) {
        return cinemaService.find(id)
                .map(value -> ResponseEntity
                        .ok(GetCinemaResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());
    }

    @PostMapping
    public ResponseEntity<Void> postCinema(@RequestBody PostCinemaRequest request, UriComponentsBuilder builder) {
        Cinema cinema = PostCinemaRequest
                .dtoToEntityMapper(() -> null)
                .apply(request);
        cinema = cinemaService.create(cinema);
        return ResponseEntity
                .created(builder
                        .pathSegment("api", "cinemas", "{id}")
                        .buildAndExpand(cinema.getId()).toUri())
                .build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCinema(@PathVariable("id") long id) {
        Optional<Cinema> cinema = cinemaService.find(id);
        if (cinema.isPresent()) {
            cinemaService.delete(cinema.get().getId());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> putCinema(@RequestBody PutCinemaRequest request, @PathVariable("id") long id) {
        Optional<Cinema> cinema = cinemaService.find(id);
        if (cinema.isPresent()) {
            PutCinemaRequest.dtoToEntityUpdater().apply(cinema.get(), request);
            cinemaService.update(cinema.get());
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
