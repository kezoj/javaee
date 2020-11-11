package film_database.director.repository;

import film_database.datastore.DataStore;
import film_database.repository.Repository;
import film_database.director.entity.Director;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Dependent
public class DirectorRepository implements Repository<Director, Long> {

    /**
     * Underlying data store.
     */
    private DataStore store;

    /**
     * @param store data store
     */
    @Inject
    public DirectorRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Director> find(Long id) {
        return store.findDirector(id);
    }

    @Override
    public List<Director> findAll()  {
        return store.findAllDirectors();
    }

    @Override
    public void create(Director entity) {
        store.createDirector(entity);
    }

    @Override
    public void delete(Director entity) {
        store.deleteDirector(entity.getId());
    }

    @Override
    public void update(Director entity) {
        store.updateDirector(entity);
    }
}
