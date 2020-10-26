package kaom.filmDistributor.view;

import kaom.filmDistributor.entity.FilmDistributor;
import kaom.filmDistributor.model.FilmDistributorEditModel;
import kaom.filmDistributor.service.FilmDistributorService;
import lombok.Getter;
import lombok.Setter;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

/**
 * View bean for rendering single filmDistributor edit form.
 */
@ViewScoped
@Named
public class FilmDistributorEdit implements Serializable {

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
    private FilmDistributorEditModel filmDistributor;



    @Inject
    public FilmDistributorEdit(FilmDistributorService service) {
        this.service = service;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached wihitn
     * field and initialized during init of the view.
     */
    public void init() throws IOException {
        Optional<FilmDistributor> filmDistributor = service.find(id);
        if (filmDistributor.isPresent()) {
            this.filmDistributor = FilmDistributorEditModel.entityToModelMapper().apply(filmDistributor.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext()
                    .responseSendError(HttpServletResponse.SC_NOT_FOUND, "FilmDistributor not found");
        }
    }

    /**
     * Action initiated by clicking save button.
     *
     * @return navigation case to the same page
     */
    public String saveAction() {
        service.update(FilmDistributorEditModel.modelToEntityUpdater().apply(service.find(id).orElseThrow(), filmDistributor));
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true&includeViewParams=true";
    }


}
