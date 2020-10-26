package kaom.film.servlet;

import kaom.film.dto.GetFilmResponse;
import kaom.film.dto.GetFilmsResponse;
import kaom.film.entity.Film;
import kaom.film.service.FilmService;
import kaom.filmDistributor.dto.GetFilmDistributorsResponse;
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

/**
 * Servlet for handling HTTP requests considering film operations.
 */
@WebServlet(urlPatterns = kaom.film.servlet.FilmServlet.Paths.FILMS + "/*")
public class FilmServlet extends HttpServlet {

    private FilmService service;

    @Inject
    public void FilmServlet(FilmService service) {
        this.service = service;
    }

    public static class Paths {

        public static final String FILMS = "/api/films";

    }

    public static class Patterns {

        public static final String FILM = "^/[0-9]+/?$";

        public static final String FILMS = "^/?$";

    }

    private final Jsonb jsonb = JsonbBuilder.create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = ServletUtility.parseRequestPath(request);
        String servletPath = request.getServletPath();
        if (kaom.film.servlet.FilmServlet.Paths.FILMS.equals(servletPath)) {
            if (path.matches(kaom.film.servlet.FilmServlet.Patterns.FILM)) {
                getFilm(request, response);
                return;
            } else if (path.matches(kaom.film.servlet.FilmServlet.Patterns.FILMS)) {
                getFilms(request, response);
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    /**
     * Sends single film as JSON or 404 error if not found.
     *
     * @param request  http request
     * @param response http response
     * @throws IOException if any input or output exception occurred
     */
    private void getFilm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(ServletUtility.parseRequestPath(request).replaceAll("/", ""));
        Optional<Film> film = service.find(id);

        if (film.isPresent()) {
            response.getWriter()
                    .write(jsonb.toJson(GetFilmResponse.entityToDtoMapper().apply(film.get())));
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * Sends all films as JSON.
     *
     * @param request  http request
     * @param response http response
     * @throws IOException if an input or output exception occurred
     */
    private void getFilms(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write(jsonb.toJson(GetFilmsResponse.entityToDtoMapper().apply(service.findAll())));
    }

}
