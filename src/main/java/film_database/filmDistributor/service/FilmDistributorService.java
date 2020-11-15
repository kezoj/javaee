package film_database.filmDistributor.service;

import film_database.filmDistributor.entity.FilmDistributor;
import film_database.filmDistributor.repository.FilmDistributorRepository;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@ApplicationScoped
@NoArgsConstructor
public class FilmDistributorService {

    private FilmDistributorRepository repository;

    @Inject
    public FilmDistributorService(FilmDistributorRepository filmDistributorRepository) {
        this.repository = filmDistributorRepository;
    }

    @Transactional
    public Optional<FilmDistributor> find(Long id) {
        return repository.find(id);
    }

    @Transactional
    public List<FilmDistributor> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void create(FilmDistributor filmDistributor) {
        repository.create(filmDistributor);
    }

    @Transactional
    public void update(FilmDistributor filmDistributor) {
        repository.update(filmDistributor);
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }
}
