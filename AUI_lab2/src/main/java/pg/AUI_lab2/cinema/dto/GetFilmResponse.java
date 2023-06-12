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
    private String category;
    private String precursor;
    private int popularity;

    public static Function<Film, GetFilmResponse> entityToDtoMapper() {
        return film -> GetFilmResponse.builder()
                .title(film.getTitle())
                .category(film.getCategory())
                .precursor(film.getPrecursor())
                .popularity(film.getPopularity())
                .build();
    }
}
