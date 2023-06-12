package pg.AUI_lab3.cinema.dto;

import lombok.*;
import pg.AUI_lab3.cinema.entity.Cinema;

import java.util.function.BiFunction;

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
public class UpdateCinemaRequest {
    private String name;

    private int yearOfEstablishment;

    public static BiFunction<Cinema, UpdateCinemaRequest, Cinema> dtoToEntityUpdater() {
        return (cinema, request) -> {
            cinema.setName(request.getName());
            cinema.setYearOfEstablishment(request.getYearOfEstablishment());
            return cinema;
        };
    }
}