package film_database.film.service;

import film_database.film.entity.Film;
import film_database.film.repository.FilmRepository;
import film_database.filmDistributor.entity.FilmDistributor;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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

    public Optional<Film> find(Long id) {
        return repository.find(id);
    }

    public List<Film> findAll() {
        return repository.findAll();
    }

    public void create(Film film) {
        repository.create(film);
    }

    public void update(Film film) {
        repository.update(film);
    }

    public void delete(Long id) {
        repository.delete(repository.find(id).orElseThrow());
    }

    public List<Film> findAllByDistributor(FilmDistributor filmDistributor) {
        return repository.findAllByDistributor(filmDistributor);
    }

    public Optional<Film> find(FilmDistributor filmDistributor, Long id) {
        return repository.findByIdAndDistributor(id, filmDistributor);
    }
}
