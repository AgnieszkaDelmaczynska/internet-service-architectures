package com.spring.lab1.cinema.dto;

import com.spring.lab1.cinema.entity.Cinema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    private String city;
    private int year_of_establishment;
    private String genre;

    public static BiFunction<Cinema, PutCinemaRequest, Cinema> dtoToEntityUpdater() {
        return (cinema, request) -> {
            cinema.setName(request.getName());
            cinema.setCity(request.getCity());
            cinema.setYear_of_establishment(request.getYear_of_establishment());
            return cinema;
        };
    }
}
