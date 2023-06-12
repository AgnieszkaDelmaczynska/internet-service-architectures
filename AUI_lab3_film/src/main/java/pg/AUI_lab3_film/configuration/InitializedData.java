package pg.AUI_lab3_film.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pg.AUI_lab3_film.film.entity.Film;
import pg.AUI_lab3_film.film.service.FilmService;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {

    private final FilmService filmService;

    @Autowired
    public InitializedData(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostConstruct
    private synchronized void init() {
        Film fantasy = Film.builder().title("Fantasy").popularity(5).build();
        Film scienceFiction = Film.builder().title("ScienceFiction").popularity(6).build();
        Film horror = Film.builder().title("Horror").popularity(8).build();

        filmService.create(fantasy);
        filmService.create(scienceFiction);
        filmService.create(horror);
    }
}
