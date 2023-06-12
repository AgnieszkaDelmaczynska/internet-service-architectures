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
public class PostFilmRequest {
    private String title;
    private int popularity;

    public static Function<PostFilmRequest, Film> entityToDtoMapper() {
        return request -> Film.builder()
                .title(request.getTitle())
                .popularity(request.getPopularity())
                .build();
    }
}
