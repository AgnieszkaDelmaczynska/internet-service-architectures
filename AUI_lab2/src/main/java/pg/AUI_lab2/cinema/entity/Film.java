package pg.AUI_lab2.cinema.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "films")
public class Film implements Serializable{
    @Id
    private String title;
    private String category;
    private String precursor;
    private int popularity;

    @OneToMany(mappedBy = "film", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Cinema> cinemas;
}
