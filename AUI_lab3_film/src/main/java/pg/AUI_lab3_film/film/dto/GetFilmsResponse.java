package pg.AUI_lab3_film.film.dto;

import lombok.*;
import pg.AUI_lab3_film.film.entity.Film;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetFilmsResponse {

    @Singular
    private List<String> films;

    public static Function<Collection<Film>, GetFilmsResponse> entityToDtoMapper() {
        return films -> {
            GetFilmsResponseBuilder response = GetFilmsResponse.builder();
            films.stream()
                    .map(Film::getTitle)
                    .forEach(response::film);
            return response.build();
        };
    }
}
