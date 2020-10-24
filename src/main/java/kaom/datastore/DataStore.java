package kaom.datastore;

import kaom.serialization.CloningUtility;
import kaom.director.entity.Director;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Data source object. Mock for a database.
 */
@Log
@ApplicationScoped
public class DataStore {

    /**
     * Set of all directors.
     */
    private Set<Director> directors = new HashSet<>();

    /**
     * Seeks for single director.
     *
     * @param id director's id
     * @return container (can be empty) with director
     */
    public synchronized Optional<Director> findDirector(Long id) {
        return directors.stream()
                .filter(character -> character.getId().equals(id))
                .findFirst()
                .map(CloningUtility::clone);
    }

    /**
     * Seeks for all directors.
     *
     * @return list (can be empty) of all directors
     */
    public synchronized List<Director> findAllDirectors() {
        return directors.stream().map(CloningUtility::clone).collect(Collectors.toList());
    }

    /**
     * Stores new director.
     *
     * @param director new director
     */
    public synchronized void createDirector(Director director) throws IllegalArgumentException {
        director.setId(findAllDirectors().stream().mapToLong(Director::getId).max().orElse(0) + 1);
        directors.add(director);
    }

    /**
     * Updates existing director.
     *
     * @param director director to be updated
     * @throws IllegalArgumentException if director with provided id does not exist
     */
    public synchronized void updateDirector(Director director) throws IllegalArgumentException {
        findDirector(director.getId()).ifPresentOrElse(
                original -> {
                    directors.remove(original);
                    directors.add(director);
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The director with id \"%d\" does not exist", director.getId()));
                });
    }

    /**
     * Deletes existing director.
     *
     * @param id director's id
     * @throws IllegalArgumentException if director with provided id does not exist
     */
    public synchronized void deleteDirector(Long id) throws IllegalArgumentException {
        findDirector(id).ifPresentOrElse(
                original -> directors.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The director with id \"%d\" does not exist", id));
                });
    }

}
