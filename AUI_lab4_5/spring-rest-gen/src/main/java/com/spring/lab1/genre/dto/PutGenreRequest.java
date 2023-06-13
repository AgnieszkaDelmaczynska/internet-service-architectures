package com.spring.lab1.genre.dto;

import com.spring.lab1.genre.entity.Genre;
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
public class PutGenreRequest {
    private int year_of_creation;
    private String precursor;
    private int popularity;

    public static BiFunction<Genre, PutGenreRequest, Genre> dtoToEntityUpdater() {
        return (genre, request) -> {
            genre.setYear_of_creation(request.getYear_of_creation());
            genre.setPrecursor(request.getPrecursor());
            genre.setPopularity(request.getPopularity());
            return genre;
        };
    }
}
