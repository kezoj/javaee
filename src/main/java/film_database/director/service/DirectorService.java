package film_database.director.service;

import film_database.director.entity.Director;
import film_database.director.repository.DirectorRepository;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for all business actions regarding director entity.
 */
@ApplicationScoped
@NoArgsConstructor
public class DirectorService {

    /**
     * Repository for director entity.
     */
    private DirectorRepository repository;

    /**
     * @param directorRepository repository for director entity
     */
    @Inject
    public DirectorService(DirectorRepository directorRepository) {
        this.repository = directorRepository;
    }

    /**
     * Finds single director.
     *
     * @param id director's id
     * @return container with director
     */
    public Optional<Director> find(Long id) {
        return repository.find(id);
    }

    /**
     * @return all directors
     */
    public List<Director> findAll() {
        return repository.findAll();
    }

    /**
     * Creates new director.
     *
     * @param director new director
     */
    public void create(Director director) {
        repository.create(director);
    }


}
