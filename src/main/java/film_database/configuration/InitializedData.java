package film_database.configuration;

import film_database.director.entity.Director;
import film_database.director.entity.Education;
import film_database.director.service.DirectorService;
import film_database.film.entity.Film;
import film_database.film.service.FilmService;
import film_database.filmDistributor.entity.FilmDistributor;
import film_database.filmDistributor.service.FilmDistributorService;
import lombok.SneakyThrows;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.InputStream;
import java.time.LocalDate;

/**
 * Listener started automatically on CDI application context initialized. Injects proxy to the services and fills
 * data store with default content.
 */
@ApplicationScoped
public class InitializedData {

    /**
     * Service for directors operations.
     */
    private final DirectorService directorService;
    private final FilmDistributorService filmDistributorService;
    private final FilmService filmService;

    @Inject
    public InitializedData(DirectorService directorService, FilmDistributorService filmDistributorService, FilmService filmService) {
        this.directorService = directorService;
        this.filmDistributorService = filmDistributorService;
        this.filmService = filmService;
    }

    public void contextInitialized(@Observes @Initialized(ApplicationScoped.class) Object init) {
        init();
    }

    /**
     * Initializes database with some example values. Should be called after creating this object. This object should
     * be created only once.
     */
    private synchronized void init() {
        Director mike = Director.builder()
                .name("Mike")
                .birthDate(LocalDate.of(1990, 10, 21))
                .education(Education.NO_EDUCATION)
                .build();

        Director kevin = Director.builder()
                .name("Kevin")
                .birthDate(LocalDate.of(2001, 1, 16))
                .education(Education.NO_EDUCATION)
                .build();

        Director sherlock = Director.builder()
                .name("Sherlock")
                .birthDate(LocalDate.of(1992, 5, 11))
                .education(Education.NO_EDUCATION)
                .build();

        Director kswiecicki = Director.builder()
                .name("Krzysztof")
                .birthDate(LocalDate.of(1998, 6, 29))
                .education(Education.NO_EDUCATION)
                .build();

        directorService.create(mike);
        directorService.create(kevin);
        directorService.create(sherlock);
        directorService.create(kswiecicki);

        FilmDistributor distributor_1 = FilmDistributor.builder()
                .name("company nr. 1")
                .creationDate(LocalDate.of(1998, 6, 29))
                .capital(1000)
                .build();

        FilmDistributor distributor_2 = FilmDistributor.builder()
                .name("company nr. 2")
                .creationDate(LocalDate.of(1998, 6, 29))
                .capital(1000)
                .build();

        filmDistributorService.create(distributor_1);
        filmDistributorService.create(distributor_2);

        Film film_1 = Film.builder()
                .director(mike)
                .filmDistributor(distributor_1)
                .releaseDate(LocalDate.of(1998, 6, 29))
                .title("ewuqri")
                .genere("adsf")
                .build();

        Film film_2 = Film.builder()
                .director(kevin)
                .filmDistributor(distributor_2)
                .releaseDate(LocalDate.of(1998, 6, 29))
                .title("es")
                .genere("add")
                .build();

        Film film_3 = Film.builder()
                .director(kevin)
                .filmDistributor(distributor_2)
                .releaseDate(LocalDate.of(1998, 6, 29))
                .title("es")
                .genere("add")
                .build();

        filmService.create(film_1);
        filmService.create(film_2);
        filmService.create(film_3);
    }

    /**
     * @param name name of the desired resource
     * @return array of bytes read from the resource
     */
    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            return is.readAllBytes();
        }
    }

}
