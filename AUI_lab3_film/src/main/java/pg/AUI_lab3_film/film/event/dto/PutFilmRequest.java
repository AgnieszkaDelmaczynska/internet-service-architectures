package pg.AUI_lab3_film.film.event.dto;

import lombok.*;
import pg.AUI_lab3_film.film.entity.Film;

import java.util.function.BiFunction;
// out
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutFilmRequest {
    private String title;

    public static BiFunction<Film, pg.AUI_lab3_film.film.dto.PutFilmRequest, Film> dtoToEntityUpdater() {
        return (producer, request) -> {
            producer.setTitle(request.getTitle());
            return producer;
        };
    }
}
