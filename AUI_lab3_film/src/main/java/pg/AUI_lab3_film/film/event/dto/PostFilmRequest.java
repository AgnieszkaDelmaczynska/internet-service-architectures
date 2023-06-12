package pg.AUI_lab3_film.film.event.dto;

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
public class PostFilmRequest {
    private String title;

    public static Function<Film, PostFilmRequest> entityToDtoMapper() {
        return entity -> PostFilmRequest.builder()
                .title(entity.getTitle())
                .build();
    }
}
