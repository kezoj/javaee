package film_database.filmDistributor.dto;


import film_database.filmDistributor.entity.FilmDistributor;
import lombok.*;

import java.time.LocalDate;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateFilmDistributorRequest {

    private Long id;
    private String name;
    private LocalDate creationDate;
    private double capital;

    public static Function<CreateFilmDistributorRequest, FilmDistributor> dtoToEntityMapper() {
        return request -> FilmDistributor.builder()
                .name(request.getName())
                .creationDate(request.getCreationDate())
                .capital(request.getCapital())
                .build();
    }

}
