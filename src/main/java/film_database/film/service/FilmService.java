package film_database.film.service;

import film_database.film.entity.Film;
import film_database.film.repository.FilmRepository;
import film_database.filmDistributor.entity.FilmDistributor;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
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

    @Transactional
    public Optional<Film> find(Long id) {
        return repository.find(id);
    }

    @Transactional
    public List<Film> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void create(Film film) {
        repository.create(film);
    }

    @Transactional
    public void update(Film film) {
        repository.update(film);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(repository.find(id).orElseThrow());
    }

    @Transactional
    public List<Film> findAllByDistributor(FilmDistributor filmDistributor) {
        return repository.findAllByDistributor(filmDistributor);
    }

    @Transactional
    public Optional<Film> find(FilmDistributor filmDistributor, Long id) {
        return repository.findByIdAndDistributor(id, filmDistributor);
    }
}
