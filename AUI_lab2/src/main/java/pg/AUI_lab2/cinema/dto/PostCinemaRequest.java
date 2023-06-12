package pg.AUI_lab2.cinema.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pg.AUI_lab2.cinema.entity.Cinema;
import pg.AUI_lab2.cinema.entity.Film;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostCinemaRequest {

    private String name;
//    private int yearOfEstablishment;
//    private String film;

    public static Function<PostCinemaRequest, Cinema> dtoToEntityMapper(
            Function<String, Film> filmFunction) {
        return request -> Cinema.builder()
                .name(request.getName())
//                .yearOfEstablishment(request.getYearOfEstablishment())
//                .film(filmFunction.apply(request.getFilm()))
                .build();
    }
}
