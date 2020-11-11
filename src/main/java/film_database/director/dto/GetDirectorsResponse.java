package film_database.director.dto;

import film_database.director.entity.Education;
import lombok.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * GET directors response. Contains list of directors.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetDirectorsResponse {

    /**
     * Represents single director in list.
     */
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Director {

        private Long id;
        private String name;
        private LocalDate birthDate;
        private Education education;

    }

    /**
     * List of directors.
     */
    @Singular
    private List<Director> directors;

    /**
     * @return mapper for convenient converting entity object to dto object
     */
    public static Function<Collection<film_database.director.entity.Director>, GetDirectorsResponse> entityToDtoMapper() {
        return directors -> {
            GetDirectorsResponse.GetDirectorsResponseBuilder response = GetDirectorsResponse.builder();
            directors.stream()
                    .map(director -> Director.builder()
                            .id(director.getId())
                            .name(director.getName())
                            .birthDate(director.getBirthDate())
                            .education(director.getEducation())
                            .build())
                    .forEach(response::director);
            return response.build();
        };
    }

}
