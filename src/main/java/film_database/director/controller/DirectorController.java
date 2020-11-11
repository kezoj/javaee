package film_database.director.controller;


import film_database.director.dto.GetDirectorsResponse;
import film_database.director.service.DirectorService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("")
public class DirectorController {

    /**
     * Service for managing directors.
     */
    private DirectorService service;

    /**
     * JAX-RS requires no-args constructor.
     */
    public DirectorController() {
    }

    /**
     * @param service service for managing directors
     */
    @Inject
    public void setService(DirectorService service) {
        this.service = service;
    }

    /**
     * @return response with available directors
     */
    @GET
    @Path("/directors")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDirectors() {
        return Response.ok(GetDirectorsResponse.entityToDtoMapper().apply(service.findAll())).build();
    }

}
