package kaom.film.dto;

import kaom.director.entity.Director;
import kaom.film.entity.Film;
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
    public static Function<kaom.film.entity.Film, GetFilmResponse> entityToDtoMapper() {
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
