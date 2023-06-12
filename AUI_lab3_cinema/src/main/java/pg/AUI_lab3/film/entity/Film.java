package pg.AUI_lab3.film.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import pg.AUI_lab3.cinema.entity.Cinema;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString()
@Entity
@Table(name = "films")
public class Film implements Serializable{
    @Id
    private String title;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "film")
    @ToString.Exclude
    private List<Cinema> cinemasOfFilm;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Film film = (Film) o;
        return title != null && Objects.equals(title, film.title);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
