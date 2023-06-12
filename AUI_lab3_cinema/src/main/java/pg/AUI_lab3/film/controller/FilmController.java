package pg.AUI_lab3.film.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pg.AUI_lab3.film.dto.CreateFilmRequest;
import pg.AUI_lab3.film.entity.Film;
import pg.AUI_lab3.film.service.FilmService;

import java.util.Optional;

@RestController
@RequestMapping("api/films")
public class FilmController {

    private final FilmService service;

    @Autowired
    public FilmController(FilmService service) {
        this.service = service;
    }

    @DeleteMapping("{title}")
    public ResponseEntity<Void> deleteFilm(@PathVariable("title") String title) {
        Optional<Film> film = service.find(title);

        if (film.isPresent()) {
            service.delete(title);
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Void> createFilm(@RequestBody CreateFilmRequest request, UriComponentsBuilder builder) {
        Film film = CreateFilmRequest.dtoToEntityMapper().apply(request);

        service.create(film);
        return ResponseEntity
                .created(builder
                        .pathSegment("api", "films", "{title}")
                        .buildAndExpand(film.getTitle()).toUri())
                .build();
    }
}
