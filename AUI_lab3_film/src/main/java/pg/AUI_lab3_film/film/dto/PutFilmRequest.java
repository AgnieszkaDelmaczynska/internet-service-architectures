package pg.AUI_lab3_film.film.dto;

import lombok.*;
import pg.AUI_lab3_film.film.entity.Film;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutFilmRequest {
    private String title;
    private int popularity;

    public static BiFunction<Film, PutFilmRequest, Film> dtoToEntityUpdater() {
        return (film, request) -> {
            //film.setTitle(request.getTitle());
            film.setPopularity(request.getPopularity());
            return film;
        };
    }
}
