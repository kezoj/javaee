package kaom.filmDistributor.dto;

import lombok.*;

import java.time.LocalDate;
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
public class GetFilmDistributorsResponse {

    /**
     * Represents single filmDistributor in list.
     */
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class FilmDistributor {

        private Long id;
        private String name;
        private LocalDate creationDate;
        private double capital;

    }

    /**
     * List of filmDistributors.
     */
    @Singular
    private List<FilmDistributor> filmDistributors;

    /**
     * @return mapper for convenient converting entity object to dto object
     */
    public static Function<Collection<kaom.filmDistributor.entity.FilmDistributor>, GetFilmDistributorsResponse> entityToDtoMapper() {
        return filmDistributors -> {
            GetFilmDistributorsResponse.GetFilmDistributorsResponseBuilder response = GetFilmDistributorsResponse.builder();
            filmDistributors.stream()
                    .map(filmDistributor -> kaom.filmDistributor.dto.GetFilmDistributorsResponse.FilmDistributor.builder()
                            .id(filmDistributor.getId())
                            .name(filmDistributor.getName())
                            .creationDate(filmDistributor.getCreationDate())
                            .capital(filmDistributor.getCapital())
                            .build())
                    .forEach(response::filmDistributor);
            return response.build();
        };
    }

}
