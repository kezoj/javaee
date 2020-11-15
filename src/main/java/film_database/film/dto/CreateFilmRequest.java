package film_database.film.dto;

import film_database.director.entity.Director;
import film_database.film.entity.Film;
import film_database.filmDistributor.entity.FilmDistributor;
import lombok.*;

import java.time.LocalDate;
import java.util.function.Function;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateFilmRequest {

    private String directorName;
    private LocalDate releaseDate;
    private String title;
    private String genere;

    
    public static Function<CreateFilmRequest, Film> dtoToEntityMapper(
            Function<String, Director> directorFunction,
            FilmDistributor filmDistributor) {
        return request -> Film.builder()
                .director(directorFunction.apply(request.getDirectorName()))
                .filmDistributor(filmDistributor)
                .releaseDate(request.getReleaseDate())
                .title(request.getTitle())
                .genere(request.getGenere())
                .build();
    }
}
