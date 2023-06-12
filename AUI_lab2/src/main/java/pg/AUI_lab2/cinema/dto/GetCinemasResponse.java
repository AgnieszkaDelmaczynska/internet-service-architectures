package pg.AUI_lab2.cinema.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

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
    }

    @Singular
    private List<Cinema> cinemas;

    public static Function<Collection<pg.AUI_lab2.cinema.entity.Cinema>, GetCinemasResponse> entityToDtoMapper() {
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
