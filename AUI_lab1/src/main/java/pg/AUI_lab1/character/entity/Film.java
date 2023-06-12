package pg.AUI_lab1.character.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Film implements Serializable{
    private String title;
    private String category;
    private String precursor;
    private int popularity;
}
