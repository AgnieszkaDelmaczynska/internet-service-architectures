package pg.AUI_lab3_film.film.dto;

import lombok.*;
import pg.AUI_lab3_film.film.entity.Film;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetFilmResponse {
    private String title;
    private int popularity;

    public static Function<Film, GetFilmResponse> entityToDtoMapper() {
        return film -> GetFilmResponse.builder()
                .title(film.getTitle())
                .popularity(film.getPopularity())
                .build();
    }
}
