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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/films/{title}/cinemas")
public class FilmCinemaController {
    private CinemaService cinemaService;
    private FilmService filmService;

    @Autowired
    public FilmCinemaController(CinemaService cinemaService, FilmService filmService) {
        this.cinemaService = cinemaService;
        this.filmService = filmService;
    }

    @GetMapping
    public ResponseEntity<GetCinemasResponse> getCinemas(@PathVariable("title") String title) {
        Optional<Film> film = filmService.find(title);

        if (film.isPresent()) {
            List<Cinema> cinemas = cinemaService.findAll(film.get());
            return ResponseEntity.ok(GetCinemasResponse.entityToDtoMapper().apply(cinemas));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<GetCinemaResponse> getCinema(@PathVariable("title") String title,
                                                       @PathVariable("id") long id) {
        return cinemaService.find(title, id)
                .map(cinema -> ResponseEntity.ok(GetCinemaResponse.entityToDtoMapper().apply(cinema)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createCinema(@PathVariable("title") String title,
                                          @RequestBody CreateCinemaRequest request,
                                          UriComponentsBuilder builder) {
        Optional<Film> film = filmService.find(title);

        if (film.isPresent()) {
            Cinema cinema = CreateCinemaRequest
                    .dtoToEntityMapper(film::get)
                    .apply(request);

            cinema = cinemaService.create(cinema);
            return ResponseEntity.created(builder.pathSegment("api", "films", "{title}", "cinemas", "{id}")
                    .buildAndExpand(film.get().getTitle(), cinema.getId()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCinema(@PathVariable("title") String title,
                                          @RequestBody UpdateCinemaRequest request,
                                          @PathVariable("id") long id) {
        Optional<Cinema> cinema = cinemaService.find(title, id);

        if (cinema.isPresent()) {
            UpdateCinemaRequest.dtoToEntityUpdater().apply(cinema.get(), request);
            cinemaService.delete(cinema.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateCinema(@PathVariable("title") String title,
                                          @RequestBody UpdateCinemaRequest request,
                                          @PathVariable("id") long id) {
        Optional<Cinema> cinema = cinemaService.find(title, id);

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
