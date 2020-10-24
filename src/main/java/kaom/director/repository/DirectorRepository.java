package kaom.director.repository;

import kaom.datastore.DataStore;
import kaom.repository.Repository;
import kaom.director.entity.Director;

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
