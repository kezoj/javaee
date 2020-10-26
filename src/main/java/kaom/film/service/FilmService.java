package kaom.film.service;

import kaom.film.entity.Film;
import kaom.film.repository.FilmRepository;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@NoArgsConstructor
public class FilmService {

    /**
     * Repository for film entity.
     */
    private FilmRepository repository;

    /**
     * @param filmRepository repository for film entity
     */
    @Inject
    public FilmService(FilmRepository filmRepository) {
        this.repository = filmRepository;
    }

    /**
     * Finds single film.
     *
     * @param id film's id
     * @return container with film
     */
    public Optional<Film> find(Long id) {
        return repository.find(id);
    }

    /**
     * @return all films
     */
    public List<Film> findAll() {
        return repository.findAll();
    }

    /**
     * Creates new film.
     *
     * @param film new film
     */
    public void create(Film film) {
        repository.create(film);
    }
}
