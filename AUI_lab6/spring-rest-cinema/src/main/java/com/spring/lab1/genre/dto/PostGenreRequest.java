package com.spring.lab1.genre.dto;

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

    public static Function<com.spring.lab1.genre.dto.PostGenreRequest, com.spring.lab1.genre.entity.Genre> dtoToEntityMapper() {
        return request -> com.spring.lab1.genre.entity.Genre.builder()
                .name(request.getName())
                .build();
    }
}
