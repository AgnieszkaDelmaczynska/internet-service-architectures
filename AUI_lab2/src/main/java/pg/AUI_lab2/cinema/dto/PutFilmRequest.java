package pg.AUI_lab2.cinema.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pg.AUI_lab2.cinema.entity.Film;

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
    private String category;
    private String precursor;
    private int popularity;

    public static BiFunction<Film, PutFilmRequest, Film> dtoToEntityUpdater() {
        return (film, request) -> {
//            film.setTitle(request.getTitle());
//            film.setCategory(request.getCategory());
//            film.setPrecursor(request.getPrecursor());
            film.setPopularity(request.getPopularity());
            return film;
        };
    }
}
