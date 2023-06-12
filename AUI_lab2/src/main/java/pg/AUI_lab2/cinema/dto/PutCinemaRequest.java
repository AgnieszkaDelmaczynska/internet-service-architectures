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

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutCinemaRequest {

    private String name;
    private int yearOfEstablishment;

    public static BiFunction<Cinema, PutCinemaRequest, Cinema> dtoToEntityUpdater() {
        return (cinema, request) -> {
            cinema.setName(request.getName());
            cinema.setYearOfEstablishment(request.getYearOfEstablishment());
            return cinema;
        };
    }
}
