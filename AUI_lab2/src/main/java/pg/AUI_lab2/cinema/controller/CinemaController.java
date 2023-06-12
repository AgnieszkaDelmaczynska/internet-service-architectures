package pg.AUI_lab2.cinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import pg.AUI_lab2.cinema.entity.Cinema;
import pg.AUI_lab2.cinema.service.CinemaService;
import pg.AUI_lab2.cinema.service.FilmService;
import pg.AUI_lab2.cinema.dto.PostCinemaRequest;
import pg.AUI_lab2.cinema.dto.GetCinemaResponse;
import pg.AUI_lab2.cinema.dto.GetCinemasResponse;
import pg.AUI_lab2.cinema.dto.PutCinemaRequest;

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
                .dtoToEntityMapper(name -> filmService.find(name).orElseThrow())
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
