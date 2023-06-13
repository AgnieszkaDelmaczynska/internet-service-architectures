package com.spring.lab1.cinema.dto;

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
public class GetCinemasResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Cinema {
        private Long id;
        private String name;
        private String city;
        private int year_of_establishment;
        private String genre;
    }

    @Singular
    private List<Cinema> cinemas;

    public static Function<Collection<com.spring.lab1.cinema.entity.Cinema>, GetCinemasResponse> entityToDtoMapper() {
        return cinemas -> {
            GetCinemasResponseBuilder response = GetCinemasResponse.builder();
            cinemas.stream()
                    .map(cinema -> Cinema.builder()
                            .id(cinema.getId())
                            .name(cinema.getName())
                            .city(cinema.getCity())
                            .year_of_establishment(cinema.getYear_of_establishment())
                            .genre(cinema.getGenre().getName())
                            .build())
                    .forEach(response::cinema);
            return response.build();
        };
    }
}
