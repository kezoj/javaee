package kaom.film.entity;

import kaom.filmDistributor.entity.FilmDistributor;
import kaom.director.entity.Director;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Film implements Serializable {

    public Long id;
    public Director director;
    public FilmDistributor filmDistributor;
    public LocalDate releaseDate;
    public String title;
    public String genere;

}
