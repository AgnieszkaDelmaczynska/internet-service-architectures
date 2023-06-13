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

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetGenreResponse {

    private String name;
    private int year_of_creation;
    private String precursor;
    private int popularity;

    public static Function<Genre, GetGenreResponse> entityToDtoMapper() {
        return genre -> GetGenreResponse.builder()
                .name(genre.getName())
                .year_of_creation(genre.getYear_of_creation())
                .precursor(genre.getPrecursor())
                .popularity(genre.getPopularity())
                .build();
    }
}
