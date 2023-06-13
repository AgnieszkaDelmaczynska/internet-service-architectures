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
public class PostGenreRequest {
    private String name;
    private int year_of_creation;
    private String precursor;
    private int popularity;

    public static Function<PostGenreRequest, Genre> dtoToEntityMapper() {
        return request -> Genre.builder()
                .name(request.getName())
                .year_of_creation(request.getYear_of_creation())
                .precursor(request.getPrecursor())
                .popularity(request.getPopularity())
                .build();
    }
}
