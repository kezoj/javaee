package film_database.film.dto;

import film_database.director.entity.Director;
import film_database.film.entity.Film;
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
public class CreateFilmRequest {

    public Director director;
    public FilmDistributor filmDistributor;
    public LocalDate releaseDate;
    public String title;
    public String genere;

    public static Function<CreateFilmRequest, Film> dtoToEntityMapper() {
        return request -> Film.builder()
                .director(request.getDirector())
                .filmDistributor(request.getFilmDistributor())
                .releaseDate(request.getReleaseDate())
                .title(request.getTitle())
                .genere(request.getGenere())
                .build();
    }

}

