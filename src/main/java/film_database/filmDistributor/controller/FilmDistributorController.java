package film_database.filmDistributor.controller;


import film_database.filmDistributor.dto.CreateFilmDistributorRequest;
import film_database.filmDistributor.dto.GetFilmDistributorResponse;
import film_database.filmDistributor.dto.GetFilmDistributorsResponse;
import film_database.filmDistributor.dto.UpdateFilmDistributorRequest;
import film_database.filmDistributor.entity.FilmDistributor;
import film_database.filmDistributor.service.FilmDistributorService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.Optional;


@Path("film_distributor/")
public class FilmDistributorController {

    /**
     * Service for managing filmDistributors.
     */
    private FilmDistributorService service;

    /**
     * JAX-RS requires no-args constructor.
     */
    public FilmDistributorController() {
    }

    /**
     * @param service service for managing filmDistributors
     */
    @Inject
    public void setService(FilmDistributorService service) {
        this.service = service;
    }

    /**
     * @return response with available filmDistributors
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFilmDistributors() {
        return Response.ok(GetFilmDistributorsResponse.entityToDtoMapper().apply(service.findAll())).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFilmDistributor(@PathParam("id") Long id) {
        Optional<FilmDistributor> filmDistributor = service.find(id);
        if (filmDistributor.isPresent()) {
            return Response.ok(GetFilmDistributorResponse.entityToDtoMapper().apply(filmDistributor.get())).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postFilmDistributor(CreateFilmDistributorRequest request) {
        FilmDistributor filmDistributor = CreateFilmDistributorRequest.dtoToEntityMapper().apply(request);
        service.create(filmDistributor);
        return Response.created(UriBuilder.fromMethod(FilmDistributorController.class, "getFilmDistributor")
                .build(filmDistributor.getId())).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteFilmDistributor(@PathParam("id") Long id) {
        Optional<FilmDistributor> filmDistributor = service.find(id);
        if (filmDistributor.isPresent()) {
            service.delete(id);
            return Response.status(Response.Status.FOUND).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putUserFilmDistributor(@PathParam("id") Long id, UpdateFilmDistributorRequest request) {
        Optional<FilmDistributor> filmDistributor = service.find(id);

        if (filmDistributor.isPresent()) {
            UpdateFilmDistributorRequest.dtoToEntityUpdater().apply(filmDistributor.get(), request);

            service.update(filmDistributor.get());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
