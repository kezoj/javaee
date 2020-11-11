package film_database.filmDistributor.repository;

import film_database.datastore.DataStore;
import film_database.filmDistributor.entity.FilmDistributor;
import film_database.repository.Repository;
import javax.enterprise.context.RequestScoped;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class FilmDistributorRepository implements Repository<FilmDistributor, Long> {

    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<FilmDistributor> find(Long id) {
        return Optional.ofNullable(em.find(FilmDistributor.class, id));
    }

    @Override
    public List<FilmDistributor> findAll() {
        return em.createQuery("select c from FilmDistributor c", FilmDistributor.class).getResultList();
    }

    @Override
    public void create(FilmDistributor entity) {
        em.persist(entity);
    }

    @Override
    public void delete(FilmDistributor entity) {
        em.remove(em.find(FilmDistributor.class, entity.getId()));
    }

    @Override
    public void update(FilmDistributor entity) {
        em.merge(entity);
    }

    @Override
    public void detach(FilmDistributor entity) {
        em.detach(entity);
    }
}
