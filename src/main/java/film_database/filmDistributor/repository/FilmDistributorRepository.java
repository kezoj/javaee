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
public class FilmDistributorRepository{

    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }


    public Optional<FilmDistributor> find(Long id) {
        return Optional.ofNullable(em.find(FilmDistributor.class, id));
    }

    public List<FilmDistributor> findAll() {
        return em.createQuery("select c from FilmDistributor c", FilmDistributor.class).getResultList();
    }


    public void create(FilmDistributor entity) {
        em.persist(entity);
    }

    public void delete(Long id) {
        em.remove(em.find(FilmDistributor.class, id));
    }


    public void update(FilmDistributor entity) {
        em.merge(entity);
    }


    public void detach(FilmDistributor entity) {
        em.detach(entity);
    }
}
