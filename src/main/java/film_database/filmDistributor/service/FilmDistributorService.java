package film_database.filmDistributor.service;

import film_database.filmDistributor.entity.FilmDistributor;
import film_database.filmDistributor.repository.FilmDistributorRepository;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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

    public Optional<FilmDistributor> find(Long id) {
        return repository.find(id);
    }

    public List<FilmDistributor> findAll() {
        return repository.findAll();
    }

    public void create(FilmDistributor filmDistributor) {
        repository.create(filmDistributor);
    }

    public void update(FilmDistributor filmDistributor) {
        repository.update(filmDistributor);
    }

    public void delete(Long id) {
        repository.delete(repository.find(id).orElseThrow());
    }
}
