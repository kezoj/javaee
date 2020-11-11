package film_database.film.dto;

import film_database.director.entity.Director;
import film_database.filmDistributor.entity.FilmDistributor;
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
public class GetFilmsResponse {

    /**
     * Represents single film in list.
     */
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Film {

        public Long id;
        public Director director;
        public FilmDistributor filmDistributor;
        public LocalDate releaseDate;
        public String title;
        public String genere;

    }

    /**
     * List of films.
     */
    @Singular
    private List<film_database.film.dto.GetFilmsResponse.Film> films;

    /**
     * @return mapper for convenient converting entity object to dto object
     */
    public static Function<Collection<film_database.film.entity.Film>, GetFilmsResponse> entityToDtoMapper() {
        return films -> {
            GetFilmsResponse.GetFilmsResponseBuilder response = GetFilmsResponse.builder();
            films.stream()
                    .map(film -> Film.builder()
                            .id(film.getId())
                            .director(film.getDirector())
                            .filmDistributor(film.getFilmDistributor())
                            .releaseDate(film.getReleaseDate())
                            .title(film.getTitle())
                            .genere(film.getGenere())
                            .build())
                    .forEach(response::film);
            return response.build();
        };
    }

}

