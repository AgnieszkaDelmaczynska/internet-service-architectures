package pg.AUI_lab3_film.film.event.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pg.AUI_lab3_film.film.entity.Film;
import pg.AUI_lab3_film.film.event.dto.PostFilmRequest;

@Repository
public class FilmEventRepository {
    private RestTemplate restTemplate;

    @Autowired
    public FilmEventRepository(@Value("${AUI_lab3_film.cinemas.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Film film) {
        restTemplate.delete("/films/{title}", film.getTitle());
    }


    public void create(Film film) {
        restTemplate.postForLocation("/films", PostFilmRequest.entityToDtoMapper().apply(film));
    }
}
