package film_database.filmDistributor.view;

import film_database.filmDistributor.entity.FilmDistributor;
import film_database.filmDistributor.model.FilmDistributorModel;
import film_database.filmDistributor.service.FilmDistributorService;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

/**
 * View bean for rendering single filmDistributors.
 */
@RequestScoped
@Named
public class FilmDistributorView implements Serializable {

    /**
     * Service for managing filmDistributors.
     */
    private final FilmDistributorService service;

    /**
     * FilmDistributor id.
     */
    @Setter
    @Getter
    private Long id;

    /**
     * FilmDistributor exposed to the view.
     */
    @Getter
    private FilmDistributorModel filmDistributor;

    @Inject
    public FilmDistributorView(FilmDistributorService service) {
        this.service = service;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached wihitn
     * field and initialized during init of the view.
     */
    public void init() throws IOException {
        Optional<FilmDistributor> filmDistributor = service.find(id);
        if (filmDistributor.isPresent()) {
            this.filmDistributor = FilmDistributorModel.entityToModelMapper().apply(filmDistributor.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext()
                    .responseSendError(HttpServletResponse.SC_NOT_FOUND, "FilmDistributor not found");
        }
    }

    
}
