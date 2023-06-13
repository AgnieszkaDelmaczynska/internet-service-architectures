package com.spring.lab1.cinema.controller;


import com.spring.lab1.cinema.dto.GetCinemaResponse;
import com.spring.lab1.cinema.dto.GetCinemasResponse;
import com.spring.lab1.cinema.dto.PostCinemaRequest;
import com.spring.lab1.cinema.dto.PutCinemaRequest;
import com.spring.lab1.cinema.entity.Cinema;
import com.spring.lab1.cinema.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.Optional;

@RestController
@RequestMapping("api/genres/{name}/cinemas")
public class GenreCinemaController {

    private CinemaService cinemaService;

    private com.spring.lab1.genre.service.GenreService genreService;

    @Autowired
    public GenreCinemaController(CinemaService cinemaService, com.spring.lab1.genre.service.GenreService genreService) {
        this.cinemaService = cinemaService;
        this.genreService = genreService;
    }

    @GetMapping
    public ResponseEntity<GetCinemasResponse> getCinemas(@PathVariable("name") String name) {
        Optional<com.spring.lab1.genre.entity.Genre> genre = genreService.find(name);
        return genre.map(value -> ResponseEntity.ok(GetCinemasResponse.entityToDtoMapper().apply(cinemaService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetCinemaResponse> getCinema(@PathVariable("name") String name,
                                                       @PathVariable("id") long id) {
        return cinemaService.find(name, id)
                .map(value -> ResponseEntity.ok(GetCinemaResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postCinema(@PathVariable("name") String namee,
                                              @RequestBody PostCinemaRequest request,
                                              UriComponentsBuilder builder) {
        Optional<com.spring.lab1.genre.entity.Genre> genre = genreService.find(namee);
        if (genre.isPresent()) {
            Cinema cinema = PostCinemaRequest
                    .dtoToEntityMapper(genre::get)
                    .apply(request);
            cinema = cinemaService.create(cinema);
            return ResponseEntity.created(builder.pathSegment("api", "genres", "{name}", "cinemas", "{id}")
                    .buildAndExpand(genre.get().getName(), cinema.getId()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCinema(@PathVariable("name") String name,
                                                @PathVariable("id") long id) {
        Optional<Cinema> cinema = cinemaService.find(name, id);
        if (cinema.isPresent()) {
            cinemaService.delete(cinema.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> putCinema(@PathVariable("name") String name,
                                             @RequestBody PutCinemaRequest request,
                                             @PathVariable("id") long id) {
        Optional<Cinema> cinema = cinemaService.find(name, id);
        if (cinema.isPresent()) {
            PutCinemaRequest.dtoToEntityUpdater().apply(cinema.get(), request);
            cinemaService.update(cinema.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
