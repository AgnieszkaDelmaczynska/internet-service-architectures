package com.spring.lab1.genre.event.dto;

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

    public static Function<Genre, PostGenreRequest> entityToDtoMapper() {
        return entity -> PostGenreRequest.builder()
                .name(entity.getName())
                .build();
    }
}
