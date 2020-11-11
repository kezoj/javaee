package film_database.filmDistributor.repository;

import film_database.datastore.DataStore;
import film_database.filmDistributor.entity.FilmDistributor;
import film_database.repository.Repository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Dependent
public class FilmDistributorRepository implements Repository<FilmDistributor, Long> {

    private DataStore store;

    @Inject
    public FilmDistributorRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<FilmDistributor> find(Long id) {
        return store.findFilmDistributor(id);
    }

    @Override
    public List<FilmDistributor> findAll()  {
        return store.findAllFilmDistributors();
    }

    @Override
    public void create(FilmDistributor entity) {
        store.createFilmDistributor(entity);
    }

    @Override
    public void delete(FilmDistributor entity) {
        store.deleteFilmDistributor(entity.getId());
    }

    @Override
    public void update(FilmDistributor entity) {
        store.updateFilmDistributor(entity);
    }
}
