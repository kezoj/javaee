package film_database.film.entity;

import film_database.filmDistributor.entity.FilmDistributor;
import film_database.director.entity.Director;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
@Entity
@Table(name = "films")
public class Film implements Serializable {

    @Getter
    @Id
    @GeneratedValue
    public Long id;

    public LocalDate releaseDate;
    public String title;
    public String genere;

    @ManyToOne
    @JoinColumn(name = "filmDistributor")
    private FilmDistributor filmDistributor;

    @ManyToOne
    @JoinColumn(name ="director")
    public Director director;

}
