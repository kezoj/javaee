package kaom.filmDistributor.dto;

import kaom.filmDistributor.entity.FilmDistributor;
import lombok.*;

import java.time.LocalDate;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetFilmDistributorResponse {

    private Long id;
    private String name;
    private LocalDate creationDate;
    private double capital;

    /**
     * @return mapper for convenient converting entity object to dto object
     */
    public static Function<kaom.filmDistributor.entity.FilmDistributor, GetFilmDistributorResponse> entityToDtoMapper() {
        return filmDistributor -> GetFilmDistributorResponse.builder()
                .id(filmDistributor.getId())
                .name(filmDistributor.getName())
                .creationDate(filmDistributor.getCreationDate())
                .capital(filmDistributor.getCapital())
                .build();
    }

}
