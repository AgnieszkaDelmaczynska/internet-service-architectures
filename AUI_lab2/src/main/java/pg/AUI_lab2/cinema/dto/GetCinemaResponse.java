package pg.AUI_lab2.cinema.dto;

import lombok.*;
import pg.AUI_lab2.cinema.entity.Cinema;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetCinemaResponse {

    private Long id;
    private String name;
    private int yearOfEstablishment;
    private String film;

    public static Function<Cinema, GetCinemaResponse> entityToDtoMapper() {
        return cinema -> GetCinemaResponse.builder()
                .id(cinema.getId())
                .name(cinema.getName())
                .yearOfEstablishment(cinema.getYearOfEstablishment())
                .film(cinema.getFilm().getTitle())
                .build();
    }
}