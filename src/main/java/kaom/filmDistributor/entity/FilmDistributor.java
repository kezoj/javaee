package kaom.filmDistributor.entity;

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
public class FilmDistributor implements Serializable {

    private Long id;
    private String name;
    private LocalDate creationDate;
    private double capital;

}
