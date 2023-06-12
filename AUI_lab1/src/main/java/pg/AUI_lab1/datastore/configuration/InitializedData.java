package pg.AUI_lab1.datastore.configuration;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pg.AUI_lab1.character.entity.Film;
import pg.AUI_lab1.character.entity.Cinema;
import pg.AUI_lab1.character.service.FilmService;
import pg.AUI_lab1.character.service.CinemaService;

import javax.annotation.PostConstruct;
import java.io.InputStream;

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
        Film fantasy = Film.builder()
                .category("fantasy")
                .title("Fantasy")
                .precursor("George MacDonald")
                .popularity(5)
                .build();

        Film scienceFiction = Film.builder()
                .category("scienceFiction")
                .title("ScienceFiction")
                .precursor("George Melies")
                .popularity(6)
                .build();

        Film horror = Film.builder()
                .category("horror")
                .title("Horror")
                .precursor("Mary Shelley")
                .popularity(8)
                .build();

        filmService.create(fantasy);
        filmService.create(scienceFiction);
        filmService.create(horror);

        Cinema kinoWarszawa = Cinema.builder()
                .name("Kino_Polska")
                .yearOfEstablishment(1920)
                .broadcastedFilms(fantasy)
                .build();

        Cinema kinoPolska = Cinema.builder()
                .name("Kino_Ameryka")
                .yearOfEstablishment(2020)
                .broadcastedFilms(scienceFiction)
                .build();

        Cinema kinoObce = Cinema.builder()
                .name("Kino_Obce")
                .yearOfEstablishment(2021)
                .broadcastedFilms(horror)
                .build();

        Cinema kinoKultura = Cinema.builder()
                .name("Kino_Kultura")
                .yearOfEstablishment(2021)
                .broadcastedFilms(fantasy)
                .build();

        cinemaService.create(kinoWarszawa);
        cinemaService.create(kinoPolska);
        cinemaService.create(kinoObce);
        cinemaService.create(kinoKultura);
    }

    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            return is.readAllBytes();
        }
    }
}
