package kaom.director.dto;

import kaom.director.entity.Director;
import kaom.director.entity.Education;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.function.Function;

/**
 * GET director response. Contains only field's which can be displayed on frontend.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetDirectorResponse {

    private Long id;
    private String name;
    private LocalDate birthDate;
    private Education education;

    /**
     * @return mapper for convenient converting entity object to dto object
     */
    public static Function<Director, GetDirectorResponse> entityToDtoMapper() {
        return director -> GetDirectorResponse.builder()
                .id(director.getId())
                .name(director.getName())
                .birthDate(director.getBirthDate())
                .education(director.getEducation())
                .build();
    }

}
