package kaom.filmDistributor.model;

import kaom.filmDistributor.entity.FilmDistributor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * JSF view model class in order to not to use entity classes. Represents single filmDistributor to be edited. Includes
 * only fields which can be edited after filmDistributor creation.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class FilmDistributorEditModel {

    private Long id;
    private String name;
    private LocalDate creationDate;
    private double capital;
    /**
     * @return mapper for convenient converting entity object to dto object
     */
    public static Function<FilmDistributor, FilmDistributorEditModel> entityToModelMapper() {
        return filmDistributor -> FilmDistributorEditModel.builder()
                .name(filmDistributor.getName())
                .creationDate(filmDistributor.getCreationDate())
                .capital(filmDistributor.getCapital())
                .build();
    }

    /**
     * @return updater for convenient updating entity object using model object
     */
    public static BiFunction<FilmDistributor, FilmDistributorEditModel, FilmDistributor> modelToEntityUpdater() {
        return (filmDistributor, request) -> {
            filmDistributor.setName(request.getName());
            filmDistributor.setCreationDate(request.getCreationDate());
            filmDistributor.setCapital(request.getCapital());
            return filmDistributor;
        };
    }

}
