package pg.AUI_lab3.cinema.dto;

import lombok.*;
import pg.AUI_lab3.cinema.entity.Cinema;

import java.util.function.Function;

/**
 * A DTO for the {@link pg.AUI_lab3.cinema.entity.Cinema} entity
 */
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
    private String film;
    private int yearOfEstablishment;

    public static Function<Cinema, GetCinemaResponse> entityToDtoMapper() {
        return cinema -> GetCinemaResponse.builder()
                .id(cinema.getId())
                .name(cinema.getName())
                .film(cinema.getFilm().getTitle())
                .yearOfEstablishment(cinema.getYearOfEstablishment())
                .build();
    }
}