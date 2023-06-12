package pg.AUI_lab3.film.dto;

import lombok.*;
import pg.AUI_lab3.film.entity.Film;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateFilmRequest {
    private String title;

    public static Function<CreateFilmRequest, Film> dtoToEntityMapper() {
        return request -> Film.builder()
                .title(request.getTitle())
                .build();
    }
}
