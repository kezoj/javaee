package film_database.film.repository;

import film_database.datastore.DataStore;
import film_database.film.entity.Film;
import film_database.filmDistributor.entity.FilmDistributor;
import film_database.repository.Repository;
import film_database.serialization.CloningUtility;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Dependent
public class FilmRepository implements Repository<Film, Long> {

    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Film> find(Long id) {
        return Optional.ofNullable(em.find(Film.class, id));
    }

    @Override
    public List<Film> findAll() {
        return em.createQuery("select c from Film c", Film.class).getResultList();
    }

    @Override
    public void create(Film entity) {
        em.persist(entity);
    }

    @Override
    public void delete(Film entity) {
        em.remove(em.find(Film.class, entity.getId()));
    }

    @Override
    public void update(Film entity) {
        em.merge(entity);
    }

    @Override
    public void detach(Film entity) {
        em.detach(entity);
    }

    public List<Film> findAllByDistributor(FilmDistributor filmDistributor) {
        return em.createQuery("select c from Film c where c.filmDistributor = :filmDistributor", Film.class)
                .setParameter("filmDistributor", filmDistributor)
                .getResultList();
    }


    public Optional<Film> findByIdAndDistributor(Long id, FilmDistributor filmDistributor) {
        try {
            return Optional.of(em.createQuery("select c from Film c where c.id = :id and c.filmDistributor = :filmDistributor", Film.class)
                    .setParameter("filmDistributor", filmDistributor)
                    .setParameter("id", id)
                    .getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

}
