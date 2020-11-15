package film_database.filmDistributor.entity;

import film_database.film.entity.Film;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
@Entity
@Table(name = "filmDistributors")
public class FilmDistributor implements Serializable{

    @Getter
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private LocalDate creationDate;
    private double capital;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "filmDistributor",
            orphanRemoval = true
    )
    private List<Film> films;
}
