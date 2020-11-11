package film_database.film.dto;

import film_database.director.entity.Director;
import film_database.filmDistributor.entity.FilmDistributor;
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
public class GetFilmResponse {

    public Long id;
    public Director director;
    public FilmDistributor filmDistributor;
    public LocalDate releaseDate;
    public String title;
    public String genere;

    /**
     * @return mapper for convenient converting entity object to dto object
     */
    public static Function<film_database.film.entity.Film, GetFilmResponse> entityToDtoMapper() {
        return film -> GetFilmResponse.builder()
                .id(film.getId())
                .director(film.getDirector())
                .filmDistributor(film.getFilmDistributor())
                .releaseDate(film.getReleaseDate())
                .title(film.getTitle())
                .genere(film.getGenere())
                .build();
    }

}
