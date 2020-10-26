package kaom.film.repository;

import kaom.datastore.DataStore;
import kaom.film.entity.Film;
import kaom.repository.Repository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;


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
