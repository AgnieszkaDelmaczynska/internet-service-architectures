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

public class PostFilmRequest {
    private String title;
    private String category;
    private String precursor;
    private int popularity;

    public static Function<PostFilmRequest, Film> dtoToEntityMapper() {
        return request -> Film.builder()
                .title(request.getTitle())
                .category(request.getCategory())
                .precursor(request.getPrecursor())
                .popularity(request.getPopularity())
                .build();
    }
}
