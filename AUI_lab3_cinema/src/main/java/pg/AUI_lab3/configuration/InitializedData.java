package pg.AUI_lab3.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pg.AUI_lab3.cinema.entity.Cinema;
import pg.AUI_lab3.cinema.service.CinemaService;
import pg.AUI_lab3.film.entity.Film;
import pg.AUI_lab3.film.service.FilmService;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {
    private final CinemaService cinemaService;

    private final FilmService filmService;

    @Autowired
    public InitializedData(CinemaService cinemaService, FilmService filmService) {
        this.cinemaService = cinemaService;
        this.filmService = filmService;
    }

    @PostConstruct
    private synchronized void init() {
        Film fantasy = Film.builder().title("Fantasy").build();
        Film scienceFiction = Film.builder().title("ScienceFiction").build();
        Film horror = Film.builder().title("Horror").build();

        filmService.create(fantasy);
        filmService.create(scienceFiction);
        filmService.create(horror);

        Cinema kinoWarszawa = Cinema.builder()
                .name("Kino_Polska")
                .yearOfEstablishment(1920)
                .film(fantasy)
                .build();

        Cinema kinoPolska = Cinema.builder()
                .name("Kino_Ameryka")
                .yearOfEstablishment(2020)
                .film(scienceFiction)
                .build();

        Cinema kinoObce = Cinema.builder()
                .name("Kino_Obce")
                .yearOfEstablishment(2021)
                .film(horror)
                .build();

        Cinema kinoKultura = Cinema.builder()
                .name("Kino_Kultura")
                .yearOfEstablishment(2021)
                .film(fantasy)
                .build();

        cinemaService.create(kinoWarszawa);
        cinemaService.create(kinoPolska);
        cinemaService.create(kinoObce);
        cinemaService.create(kinoKultura);
    }
}
