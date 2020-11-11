package film_database.film.repository;

import film_database.datastore.DataStore;
import film_database.film.entity.Film;
import film_database.filmDistributor.entity.FilmDistributor;
import film_database.repository.Repository;
import film_database.serialization.CloningUtility;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Dependent
public class FilmRepository implements Repository<Film, Long> {

    /**
     * Underlying data store.
     */
    private DataStore store;

    /**
     * @param store data store
     */
    @Inject
    public FilmRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Film> find(Long id) {
        return store.findFilm(id);
    }

    @Override
    public List<Film> findAll()  {
        return store.findAllFilms();
    }

    public List<Film> findAllByDistributor(FilmDistributor filmDistributor) {
        return store.getFilmStream()
                .filter(film -> film.getFilmDistributor().equals(filmDistributor))
                .map(CloningUtility::clone)
                .collect(Collectors.toList());
    }

    public Optional<Film> findByIdAndDistributor(Long id, FilmDistributor filmDistributor) {
        return store.getFilmStream()
                .filter(film -> film.getFilmDistributor().equals(filmDistributor))
                .filter(film -> film.getId().equals(id))
                .findFirst()
                .map(CloningUtility::clone);
    }


    @Override
    public void create(Film entity) {
        store.createFilm(entity);
    }

    @Override
    public void delete(Film entity) {
        store.deleteFilm(entity.getId());
    }

    @Override
    public void update(Film entity) {
        store.updateFilm(entity);
    }
}
