package pg.AUI_lab3.cinema.dto;

import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * A DTO for the {@link pg.AUI_lab3.cinema.entity.Cinema} entity
 */
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
    }

    @Singular
    private List<Cinema> cinemas;

    public static Function<Collection<pg.AUI_lab3.cinema.entity.Cinema>, GetCinemasResponse> entityToDtoMapper() {
        return cinemas -> {
            GetCinemasResponseBuilder response = GetCinemasResponse.builder();
            cinemas.stream()
                    .map(cinema -> Cinema.builder()
                            .id(cinema.getId())
                            .name(cinema.getName())
                            .build())
                    .forEach(response::cinema);
            return response.build();
        };
    }
}