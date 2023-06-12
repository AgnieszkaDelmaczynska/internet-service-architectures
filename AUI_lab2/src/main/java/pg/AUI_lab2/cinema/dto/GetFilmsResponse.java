package pg.AUI_lab2.cinema.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

import javax.persistence.Id;
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
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Film {
        private String title;
        private int popularity;
    }

    @Singular
    private List<GetFilmsResponse.Film> films;

    public static Function<Collection<pg.AUI_lab2.cinema.entity.Film>, GetFilmsResponse> entityToDtoMapper() {
        return films -> {
            GetFilmsResponse.GetFilmsResponseBuilder response = GetFilmsResponse.builder();
            films.stream()
                    .map(film -> GetFilmsResponse.Film.builder()
                            .title(film.getTitle())
                            .popularity(film.getPopularity())
                            .build())
                    .forEach(response::film);
            return response.build();
        };
    }
}
