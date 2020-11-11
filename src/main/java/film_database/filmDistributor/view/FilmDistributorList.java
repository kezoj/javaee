package film_database.filmDistributor.view;

import film_database.filmDistributor.model.FilmDistributorsModel;
import film_database.filmDistributor.service.FilmDistributorService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * View bean for rendering list of filmDistributors.
 */
@RequestScoped
@Named
public class FilmDistributorList implements Serializable {

    /**
     * Service for managing filmDistributors.
     */
    private final FilmDistributorService service;

    /**
     * FilmDistributors list exposed to the view.
     */
    private FilmDistributorsModel filmDistributors;

    @Inject
    public FilmDistributorList(FilmDistributorService service) {
        this.service = service;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached using
     * lazy getter.
     *
     * @return all filmDistributors
     */
    public FilmDistributorsModel getFilmDistributors() {
        return filmDistributors = FilmDistributorsModel.entityToModelMapper().apply(service.findAll());
    }

    /**
     * Action for clicking delete action.
     *
     * @param filmDistributor filmDistributor to be removed
     * @return navigation case to list_filmDistributors
     */
    public String deleteAction(FilmDistributorsModel.FilmDistributor filmDistributor) {
        service.delete(filmDistributor.getId());
        return "film_distributor_list?faces-redirect=true";
    }

}
