package film_database.filmDistributor.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * JSF view model class in order to not to use entity classes. Represents list of filmDistributors to be displayed.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class FilmDistributorsModel implements Serializable {

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

    }

    /**
     * Name of the selected filmDistributors.
     */
    @Singular
    private List<FilmDistributor> filmDistributors;

    /**
     * @return mapper for convenient converting entity object to model object
     */
    public static Function<Collection<film_database.filmDistributor.entity.FilmDistributor>, FilmDistributorsModel> entityToModelMapper() {
        return filmDistributors -> {
            FilmDistributorsModel.FilmDistributorsModelBuilder model = FilmDistributorsModel.builder();
            filmDistributors.stream()
                    .map(filmDistributor -> FilmDistributor.builder()
                            .id(filmDistributor.getId())
                            .name(filmDistributor.getName())
                            .build())
                    .forEach(model::filmDistributor);
            return model.build();
        };
    }

}
