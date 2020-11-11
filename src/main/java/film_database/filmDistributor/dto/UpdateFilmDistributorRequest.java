package film_database.filmDistributor.dto;

import film_database.filmDistributor.entity.FilmDistributor;
import lombok.*;

import java.time.LocalDate;
import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateFilmDistributorRequest {

    private Long id;
    private String name;
    private LocalDate creationDate;
    private double capital;


    public static BiFunction<FilmDistributor, UpdateFilmDistributorRequest, FilmDistributor> dtoToEntityUpdater() {
        return (filmDistributor, request) -> {
            filmDistributor.setName(request.getName());
            filmDistributor.setCreationDate(request.getCreationDate());
            filmDistributor.setCapital(request.getCapital());
            return filmDistributor;
        };
    }
}
