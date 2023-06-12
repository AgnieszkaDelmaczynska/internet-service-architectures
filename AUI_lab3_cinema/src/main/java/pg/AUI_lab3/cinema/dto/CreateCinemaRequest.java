package pg.AUI_lab3.cinema.dto;

import lombok.*;
import pg.AUI_lab3.cinema.entity.Cinema;
import pg.AUI_lab3.film.entity.Film;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A DTO for the {@link Cinema} entity
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateCinemaRequest {
    private String name;
    private String film;
    private int yearOfEstablishment;

    public static Function<CreateCinemaRequest, Cinema> dtoToEntityMapper(
            Supplier<Film> producerSupplier) {
        return request -> Cinema.builder()
                .name(request.getName())
                .film(producerSupplier.get())
                .yearOfEstablishment(request.getYearOfEstablishment())
                .build();
    }
}