package film_database.controller;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Global config for JAX-RS REST services prefix.
 */
@ApplicationPath("/api/")
public class Config extends Application {
}
