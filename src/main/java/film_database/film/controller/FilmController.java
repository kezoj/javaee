package film_database.film.controller;

import film_database.director.service.DirectorService;
import film_database.film.dto.CreateFilmRequest;
import film_database.film.dto.GetFilmResponse;
import film_database.film.dto.GetFilmsResponse;
import film_database.film.dto.UpdateFilmRequest;
import film_database.film.entity.Film;
import film_database.film.service.FilmService;
import film_database.filmDistributor.entity.FilmDistributor;
import film_database.filmDistributor.model.FilmDistributorsModel;
import film_database.filmDistributor.service.FilmDistributorService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.Optional;


@Path("film_distributor/")
public class FilmController {

    /**
     * Service for managing films.
     */
    private FilmService service;
    private FilmDistributorService filmDistributorService;
    private DirectorService directorService;

    /**
     * JAX-RS requires no-args constructor.
     */
    public FilmController() {
    }

    /**
     * @param service service for managing films
     */
    @Inject
    public void setService(FilmService service, FilmDistributorService filmDistributorService, DirectorService directorService) {
        this.service = service;
        this.filmDistributorService = filmDistributorService;
        this.directorService = directorService;
    }

    /**
     * @return response with available films
     */
    @GET
    @Path("{film_distributor_id}/film")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFilms(@PathParam("film_distributor_id") Long film_distributor_id) {
        Optional<FilmDistributor> filmDistributor = filmDistributorService.find(film_distributor_id);
        if (filmDistributor.isPresent()){
            return Response.ok(GetFilmsResponse.entityToDtoMapper().apply(service.findAllByDistributor(filmDistributor.get()))).build();
        }
        else
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("{film_distributor_id}/film/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFilm(@PathParam("film_distributor_id") Long film_distributor_id, @PathParam("id") Long id) {
        Optional<FilmDistributor> filmDistributor = filmDistributorService.find(film_distributor_id);
        if (filmDistributor.isPresent()){
            Optional<Film> film = service.find(filmDistributor.get(), id);
            if (film.isPresent()) {
                return Response.ok(GetFilmResponse.entityToDtoMapper().apply(film.get())).build();
            }
            else
            {
                return Response.status(Response.Status.OK).build();
            }
        }
        else
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @POST
    @Path("{film_distributor_id}/film")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postFilm(@PathParam("film_distributor_id") Long filmDistributorId, CreateFilmRequest request) {
        Optional<FilmDistributor> filmDistributor = filmDistributorService.find(filmDistributorId);

        if (filmDistributor.isPresent()) {
            Film film = CreateFilmRequest.dtoToEntityMapper(
                    name -> directorService.findByName(name).orElse(null),
                    filmDistributor.get())
                    .apply(request);

            service.create(film);
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{film_distributor_id}/film/{id}")
    public Response deleteFilm(@PathParam("film_distributor_id") Long film_distributor_id, @PathParam("id") Long id) {
        Optional<FilmDistributor> filmDistributor = filmDistributorService.find(film_distributor_id);
        if (filmDistributor.isPresent()){
            Optional<Film> film = service.find(filmDistributor.get(), id);
            if (film.isPresent()) {
                service.delete(id);
                return Response.status(Response.Status.OK).build();
            }
            else
            {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
        else
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @PUT
    @Path("{film_distributor_id}/film/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putUserFilm(@PathParam("film_distributor_id") Long film_distributor_id, @PathParam("id") Long id, UpdateFilmRequest request) {
        Optional<FilmDistributor> filmDistributor = filmDistributorService.find(film_distributor_id);
        if (filmDistributor.isPresent()) {
            Optional<Film> film = service.find(filmDistributor.get(), id);
            if (film.isPresent()) {
                UpdateFilmRequest.dtoToEntityUpdater().apply(film.get(), request);
                service.update(film.get());
                return Response.status(Response.Status.OK).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public boolean checkForFilmDistributor(Long film_distributor_id) {
        Optional<FilmDistributor> filmDistributor = filmDistributorService.find(film_distributor_id);
        return filmDistributor.isPresent();
    }
}

