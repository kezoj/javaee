package film_database.datastore;

import film_database.film.entity.Film;
import film_database.filmDistributor.entity.FilmDistributor;
import film_database.serialization.CloningUtility;
import film_database.director.entity.Director;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Data source object. Mock for a database.
 */
@Log
@ApplicationScoped
public class DataStore {

    private Set<Director> directors = new HashSet<>();
    private Set<FilmDistributor> filmDistributors = new HashSet<>();
    private Set<Film> films = new HashSet<>();


    public synchronized Optional<Director> findDirector(Long id) {
        return directors.stream()
                .filter(character -> character.getId().equals(id))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized List<Director> findAllDirectors() {
        return directors.stream().map(CloningUtility::clone).collect(Collectors.toList());
    }

    public synchronized void createDirector(Director director) throws IllegalArgumentException {
        director.setId(findAllDirectors().stream().mapToLong(Director::getId).max().orElse(0) + 1);
        directors.add(director);
    }

    public synchronized void updateDirector(Director director) throws IllegalArgumentException {
        findDirector(director.getId()).ifPresentOrElse(
                original -> {
                    directors.remove(original);
                    directors.add(director);
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The director with id \"%d\" does not exist", director.getId()));
                });
    }

    public synchronized void deleteDirector(Long id) throws IllegalArgumentException {
        findDirector(id).ifPresentOrElse(
                original -> directors.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The director with id \"%d\" does not exist", id));
                });
    }



    public synchronized Optional<FilmDistributor> findFilmDistributor(Long id) {
        return filmDistributors.stream()
                .filter(character -> character.getId().equals(id))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized List<FilmDistributor> findAllFilmDistributors() {
        return filmDistributors.stream().map(CloningUtility::clone).collect(Collectors.toList());
    }

    public synchronized void createFilmDistributor(FilmDistributor filmDistributor) throws IllegalArgumentException {
        filmDistributor.setId(findAllFilmDistributors().stream().mapToLong(FilmDistributor::getId).max().orElse(0) + 1);
        filmDistributors.add(filmDistributor);
    }

    public synchronized void updateFilmDistributor(FilmDistributor filmDistributor) throws IllegalArgumentException {
        findFilmDistributor(filmDistributor.getId()).ifPresentOrElse(
                original -> {
                    filmDistributors.remove(original);
                    filmDistributors.add(filmDistributor);
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The director with id \"%d\" does not exist", filmDistributor.getId()));
                });
    }

    public synchronized void deleteFilmDistributor(Long id) throws IllegalArgumentException {
        findFilmDistributor(id).ifPresentOrElse(
                original -> filmDistributors.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The director with id \"%d\" does not exist", id));
                });
    }



    public synchronized Optional<Film> findFilm(Long id) {
        return films.stream()
                .filter(character -> character.getId().equals(id))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized List<Film> findAllFilms() {
        return films.stream().map(CloningUtility::clone).collect(Collectors.toList());
    }

    public synchronized void createFilm(Film film) throws IllegalArgumentException {
        film.setId(findAllFilms().stream().mapToLong(Film::getId).max().orElse(0) + 1);
        films.add(film);
    }

    public synchronized void updateFilm(Film film) throws IllegalArgumentException {
        findFilm(film.getId()).ifPresentOrElse(
                original -> {
                    films.remove(original);
                    films.add(film);
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The director with id \"%d\" does not exist", film.getId()));
                });
    }

    public synchronized void deleteFilm(Long id) throws IllegalArgumentException {
        findFilm(id).ifPresentOrElse(
                original -> films.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The director with id \"%d\" does not exist", id));
                });
    }

    public Stream<Film> getFilmStream() {
        return films.stream();
    }
}
