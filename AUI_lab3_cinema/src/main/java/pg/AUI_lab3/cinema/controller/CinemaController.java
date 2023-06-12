package pg.AUI_lab3.cinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pg.AUI_lab3.cinema.dto.CreateCinemaRequest;
import pg.AUI_lab3.cinema.dto.GetCinemaResponse;
import pg.AUI_lab3.cinema.dto.GetCinemasResponse;
import pg.AUI_lab3.cinema.dto.UpdateCinemaRequest;
import pg.AUI_lab3.cinema.entity.Cinema;
import pg.AUI_lab3.cinema.service.CinemaService;
import pg.AUI_lab3.film.entity.Film;
import pg.AUI_lab3.film.service.FilmService;

import java.util.Optional;

@RestController
@RequestMapping("api/cinemas")
public class CinemaController {

    private CinemaService cinemaService;
    private FilmService filmService;

    @Autowired
    public CinemaController(CinemaService cinemaService, FilmService filmService) {
        this.cinemaService = cinemaService;
        this.filmService = filmService;
    }

    @GetMapping
    public ResponseEntity<GetCinemasResponse> getCinemas() {
        return ResponseEntity
                .ok(GetCinemasResponse.entityToDtoMapper().apply(cinemaService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetCinemaResponse> getCinema(@PathVariable("id") Long id) {
        Optional<Cinema> cinema = cinemaService.find(id);
        return cinema.map(value -> ResponseEntity.ok(GetCinemaResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Long> createCar(@RequestBody CreateCinemaRequest request, UriComponentsBuilder builder) {
        Optional<Film> film = filmService.find(request.getFilm());

        if (film.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Cinema cinema = CreateCinemaRequest
                .dtoToEntityMapper(film::get)
                .apply(request);

        cinemaService.create(cinema);

        return ResponseEntity
                .created(builder
                        .pathSegment("api", "cinemas", "{id}").buildAndExpand(cinema.getId()).toUri())
                .body(cinema.getId());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCinema(@PathVariable("id") Long id) {
        Optional<Cinema> cinema = cinemaService.find(id);

        if (cinema.isPresent()) {
            cinemaService.delete(cinema.get().getId());

            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateCinema(@RequestBody UpdateCinemaRequest request, @PathVariable("id") long id) {
        Optional<Cinema> cinema = cinemaService.find(id);

        if (cinema.isPresent()) {
            UpdateCinemaRequest.dtoToEntityUpdater().apply(cinema.get(), request);
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
