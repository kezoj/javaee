package film_database.director.repository;

import film_database.datastore.DataStore;
import film_database.director.entity.Director;
import film_database.repository.Repository;
import javax.enterprise.context.RequestScoped;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class DirectorRepository implements Repository<Director, Long> {

    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Director> find(Long id) {
        return Optional.ofNullable(em.find(Director.class, id));
    }

    @Override
    public List<Director> findAll() {
        return em.createQuery("select c from Director c", Director.class).getResultList();
    }

    @Override
    public void create(Director entity) {
        em.persist(entity);
    }

    @Override
    public void delete(Director entity) {
        em.remove(em.find(Director.class, entity.getId()));
    }

    @Override
    public void update(Director entity) {
        em.merge(entity);
    }

    @Override
    public void detach(Director entity) {
        em.detach(entity);
    }
}
