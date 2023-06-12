package pg.AUI_lab2.cinema.controller;

import com.sun.istack.NotNull;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import pg.AUI_lab2.cinema.dto.*;
import pg.AUI_lab2.cinema.entity.Cinema;
import pg.AUI_lab2.cinema.entity.Film;
import pg.AUI_lab2.cinema.service.CinemaService;
import pg.AUI_lab2.cinema.service.FilmService;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("api/films")
public class FilmController {
    private CinemaService cinemaService;
    private FilmService filmService;

    @Autowired
    public FilmController(CinemaService cinemaService, FilmService filmService) {
        this.cinemaService = cinemaService;
        this.filmService = filmService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})//When empty JSON is default
    public ResponseEntity<GetFilmsResponse> getFilms() {
        return ResponseEntity
                .ok(GetFilmsResponse.entityToDtoMapper().apply(filmService.findAll()));
    }

    @GetMapping("{title}")
    public ResponseEntity<GetFilmResponse> getFilm(@PathVariable("title") String title) {
        return filmService.find(title)
                .map(value -> ResponseEntity
                        .ok(GetFilmResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());
    }

    @GetMapping("{title}/cinemas")
    public ResponseEntity<GetCinemasResponse> getCinemas(@PathVariable("title") String title) {
        Optional<Film> film = filmService.find(title);
        return film.map(value -> ResponseEntity.ok(GetCinemasResponse.entityToDtoMapper().apply(cinemaService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{title}/cinemas/{id}")
    public ResponseEntity<GetCinemaResponse> getCinema(@PathVariable("title") String title,
                                                             @PathVariable("id") long id) {
        return cinemaService.find(title, id)
                .map(value -> ResponseEntity
                        .ok(GetCinemaResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());
    }

    @PostMapping
    public ResponseEntity<Void> postFilm(@RequestBody PostFilmRequest request, @NotNull UriComponentsBuilder builder){
        Film film = PostFilmRequest
                .dtoToEntityMapper()
                .apply(request);
        film = filmService.create(film);
        return ResponseEntity
                .created(builder
                        .pathSegment("api","films","{title}")
                        .buildAndExpand(film.getTitle()).toUri())
                .build();
    }

    @PutMapping("{title}")
    public ResponseEntity<Void> putFilm(@PathVariable("title") String title,
                                             @RequestBody PutFilmRequest request) {
        Optional<Film> film = filmService.find(title);
        if (film.isPresent()) {
            PutFilmRequest.dtoToEntityUpdater().apply(film.get(), request);
            filmService.update(film.get());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @DeleteMapping("{title}")
    public ResponseEntity<Void> deleteFilm(@PathVariable("title") String title) {
        Optional<Film> film = filmService.find(title);
        if (film.isPresent()) {
            filmService.delete(film.get().getTitle());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
