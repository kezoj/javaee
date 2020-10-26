package kaom.director.servlet;

import kaom.filmDistributor.dto.GetFilmDistributorsResponse;
import kaom.filmDistributor.service.FilmDistributorService;
import kaom.servlet.ServletUtility;
import kaom.director.dto.GetDirectorResponse;
import kaom.director.dto.GetDirectorsResponse;
import kaom.director.entity.Director;
import kaom.director.service.DirectorService;

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
 * Servlet for handling HTTP requests considering director operations.
 */
@WebServlet(urlPatterns = DirectorServlet.Paths.USERS + "/*")
public class DirectorServlet extends HttpServlet {

    private DirectorService service;

    @Inject
    public DirectorServlet(DirectorService service) {
        this.service = service;
    }

    public static class Paths {

        public static final String USERS = "/api/directors";

    }

    public static class Patterns {

        public static final String USER = "^/[0-9]+/?$";

        public static final String USERS = "^/?$";

    }

    private final Jsonb jsonb = JsonbBuilder.create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = ServletUtility.parseRequestPath(request);
        String servletPath = request.getServletPath();
        if (Paths.USERS.equals(servletPath)) {
            if (path.matches(Patterns.USER)) {
                getDirector(request, response);
                return;
            } else if (path.matches(Patterns.USERS)) {
                getDirectors(request, response);
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    /**
     * Sends single director as JSON or 404 error if not found.
     *
     * @param request  http request
     * @param response http response
     * @throws IOException if any input or output exception occurred
     */
    private void getDirector(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(ServletUtility.parseRequestPath(request).replaceAll("/", ""));
        Optional<Director> director = service.find(id);

        if (director.isPresent()) {
            response.getWriter()
                    .write(jsonb.toJson(GetDirectorResponse.entityToDtoMapper().apply(director.get())));
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * Sends all directors as JSON.
     *
     * @param request  http request
     * @param response http response
     * @throws IOException if an input or output exception occurred
     */
    private void getDirectors(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write(jsonb.toJson(GetDirectorsResponse.entityToDtoMapper().apply(service.findAll())));
    }

}
