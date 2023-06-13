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

import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostCinemaRequest {
    private String name;
    private String city;
    private int year_of_establishment;
    private String genre;

    public static Function<PostCinemaRequest, Cinema> dtoToEntityMapper(Supplier<com.spring.lab1.genre.entity.Genre> genreSupplier) {
        return request -> Cinema.builder()
                .name(request.getName())
                .city(request.getCity())
                .year_of_establishment(request.getYear_of_establishment())
                .genre(genreSupplier.get())
                .build();
    }

}
