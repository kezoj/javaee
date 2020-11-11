package film_database.filmDistributor.model;

import film_database.filmDistributor.entity.FilmDistributor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.function.Function;

/**
 * JSF view model class in order to not to use entity classes. Represents single filmDistributor to be displayed.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class FilmDistributorModel {

    private Long id;
    private String name;
    private LocalDate creationDate;
    private double capital;

    /**
     * @return mapper for convenient converting entity object to dto object
     */
    public static Function<FilmDistributor, FilmDistributorModel> entityToModelMapper() {
        return filmDistributor -> FilmDistributorModel.builder()
                .name(filmDistributor.getName())
                .creationDate(filmDistributor.getCreationDate())
                .capital(filmDistributor.getCapital())
                .build();
    }
}
