package kaom.film.entity;

import kaom.FilmDistributor.entity.FilmDistributor;
import kaom.director.entity.Director;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map.Entry;

public class Film {

    public Director director;
    public FilmDistributor movie_distributor;
    public LocalDate releaseDate;
    public String title;
    public String genere;

}
