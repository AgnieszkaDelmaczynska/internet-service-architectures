package com.spring.lab1.genre.dto;

import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetGenresResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Genre {
        private String name;
        private int year_of_creation;
        private String precursor;
        private int popularity;
    }

    @Singular
    private List<GetGenresResponse.Genre> genres;

    public static Function<Collection<com.spring.lab1.genre.entity.Genre>, GetGenresResponse> entityToDtoMapper() {
        return genres -> {
            GetGenresResponse.GetGenresResponseBuilder response = GetGenresResponse.builder();
            genres.stream()
                    .map(genre -> Genre.builder()
                            .name(genre.getName())
                            .year_of_creation(genre.getYear_of_creation())
                            .precursor(genre.getPrecursor())
                            .popularity(genre.getPopularity())
                            .build())
                    .forEach(response::genre);
            return response.build();
        };
    }
}
