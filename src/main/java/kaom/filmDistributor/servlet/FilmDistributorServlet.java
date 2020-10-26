package kaom.filmDistributor.servlet;

import kaom.director.servlet.DirectorServlet;
import kaom.filmDistributor.dto.GetFilmDistributorResponse;
import kaom.filmDistributor.dto.GetFilmDistributorsResponse;
import kaom.filmDistributor.entity.FilmDistributor;
import kaom.filmDistributor.service.FilmDistributorService;
import kaom.servlet.ServletUtility;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@WebServlet(urlPatterns = kaom.filmDistributor.servlet.FilmDistributorServlet.Paths.FILM_DISTRIBUTOR + "/*")
public class FilmDistributorServlet extends HttpServlet {

    private FilmDistributorService service;

    @Inject
    public FilmDistributorServlet(FilmDistributorService service) {
        this.service = service;
    }

    public static class Paths {

        public static final String FILM_DISTRIBUTOR = "/api/film_distributor";

    }

    public static class Patterns {

        public static final String FILM_DISTRIBUTOR = "^/[0-9]+/?$";

        public static final String FILM_DISTRIBUTORS = "^/?$";

    }

    private final Jsonb jsonb = JsonbBuilder.create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = ServletUtility.parseRequestPath(request);
        String servletPath = request.getServletPath();
        if (FilmDistributorServlet.Paths.FILM_DISTRIBUTOR.equals(servletPath)) {
            if (path.matches(FilmDistributorServlet.Patterns.FILM_DISTRIBUTOR)) {
                getFilmDistributor(request, response);
                return;
            } else if (path.matches(FilmDistributorServlet.Patterns.FILM_DISTRIBUTORS)) {
                getFilmDistributors(request, response);
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    /**
     * Sends single filmDistributor as JSON or 404 error if not found.
     *
     * @param request  http request
     * @param response http response
     * @throws IOException if any input or output exception occurred
     */
    private void getFilmDistributor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(ServletUtility.parseRequestPath(request).replaceAll("/", ""));
        Optional<FilmDistributor> filmDistributor = service.find(id);

        if (filmDistributor.isPresent()) {
            response.getWriter()
                    .write(jsonb.toJson(GetFilmDistributorResponse.entityToDtoMapper().apply(filmDistributor.get())));
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * Sends all filmDistributors as JSON.
     *
     * @param request  http request
     * @param response http response
     * @throws IOException if an input or output exception occurred
     */
    private void getFilmDistributors(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write(jsonb.toJson(GetFilmDistributorsResponse.entityToDtoMapper().apply(service.findAll())));
    }

}
