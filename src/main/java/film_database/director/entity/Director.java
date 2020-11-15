package film_database.director.entity;

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
@Table(name = "director")
public class Director implements Serializable {

    @Getter
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private LocalDate birthDate;
    private Education education;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "director"
    )
    private List<Film> films;
}
