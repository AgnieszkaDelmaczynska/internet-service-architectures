package com.spring.lab1.cinema.dto;

import com.spring.lab1.cinema.entity.Cinema;
import lombok.*;

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

    private String city;

    private int year_of_establishment;

    private String genre;

    public static Function<Cinema, GetCinemaResponse> entityToDtoMapper() {
        return cinema -> GetCinemaResponse.builder()
                .id(cinema.getId())
                .name(cinema.getName())
                .city(cinema.getCity())
                .year_of_establishment(cinema.getYear_of_establishment())
                .genre(cinema.getGenre().getName())
                .build();
    }
}
