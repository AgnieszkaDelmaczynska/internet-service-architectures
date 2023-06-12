package pg.AUI_lab3_film.film.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pg.AUI_lab3_film.film.dto.GetFilmResponse;
import pg.AUI_lab3_film.film.dto.GetFilmsResponse;
import pg.AUI_lab3_film.film.dto.PostFilmRequest;
import pg.AUI_lab3_film.film.dto.PutFilmRequest;
import pg.AUI_lab3_film.film.entity.Film;
import pg.AUI_lab3_film.film.service.FilmService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/films")
public class FilmController {

    private FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public ResponseEntity<GetFilmsResponse> getFilms() {
        List<Film> all = filmService.findAll();
        Function<Collection<Film>, GetFilmsResponse> mapper = GetFilmsResponse.entityToDtoMapper();
        GetFilmsResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{title}")
    public ResponseEntity<GetFilmResponse> getFilm(@PathVariable("title") String title) {
        return filmService.find(title)
                .map(value -> ResponseEntity.ok(GetFilmResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{title}")
    public ResponseEntity<Void> deleteFilm(@PathVariable("title") String title) {
        Optional<Film> film = filmService.find(title);
        if (film.isPresent()) {
            filmService.delete(film.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Void> createFilm(@RequestBody PostFilmRequest request, UriComponentsBuilder builder) {
        Film film = PostFilmRequest.entityToDtoMapper().apply(request);
        filmService.create(film);
        return ResponseEntity.created(builder.pathSegment("api", "films", "{title}")
                .buildAndExpand(film.getTitle()).toUri()).build();
    }

    @PutMapping("{title}")
    public ResponseEntity<Void> updateFilm(@RequestBody PutFilmRequest request, @PathVariable("title") String title) {
        Optional<Film> film = filmService.find(title);

        if (film.isPresent()) {
            PutFilmRequest.dtoToEntityUpdater().apply(film.get(), request);
            filmService.update(film.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
