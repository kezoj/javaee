package kaom.director.service;

import kaom.director.entity.Director;
import kaom.director.repository.DirectorRepository;
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

    /**
     * Uploads director's avatar.
     * Fails when director already has an avatar.
     *
     * @param id director's id
     * @param is input stream containing new avatar
     */
    public void uploadAvatar(Long id, InputStream is) {
        repository.find(id).ifPresent(director -> {
            try {
                if (director.getAvatar() == null) {
                    director.setAvatar(is.readAllBytes());
                    repository.update(director);
                }
            } catch (IOException ex) {
                throw new IllegalStateException(ex);
            }
        });
    }

    /**
     * Updates director's avatar.
     *
     * @param id director's id
     * @param is input stream containing new avatar
     */
    public void updateAvatar(Long id, InputStream is) {
        repository.find(id).ifPresent(director -> {
            try {
                director.setAvatar(is.readAllBytes());
                repository.update(director);
            } catch (IOException ex) {
                throw new IllegalStateException(ex);
            }
        });
    }

    /**
     * Deletes director's avatar.
     *
     * @param id director's id
     */
    public void deleteAvatar(Long id) {
        repository.find(id).ifPresent(director -> {
                director.setAvatar(null);
                repository.update(director);
        });
    }
}
