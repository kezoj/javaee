package kaom.director.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Entity for system director. Represents information about particular director.
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Director implements Serializable {

    private Long id;
    private String name;
    private LocalDate birthDate;
    private Education education;
    private byte[] avatar;

}
