package film_database.film.dto;

import film_database.director.entity.Director;
import film_database.film.entity.Film;
import film_database.filmDistributor.entity.FilmDistributor;
import lombok.*;

import java.time.LocalDate;
import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateFilmRequest {

    public Director director;
    public FilmDistributor filmDistributor;
    public LocalDate releaseDate;
    public String title;
    public String genere;


    public static BiFunction<Film, UpdateFilmRequest, Film> dtoToEntityUpdater() {
        return (filmDistributor, request) -> {
            filmDistributor.setDirector(request.getDirector());
            filmDistributor.setFilmDistributor(request.getFilmDistributor());
            filmDistributor.setReleaseDate(request.getReleaseDate());
            filmDistributor.setTitle(request.getTitle());
            filmDistributor.setGenere(request.getGenere());
            return filmDistributor;
        };
    }
}