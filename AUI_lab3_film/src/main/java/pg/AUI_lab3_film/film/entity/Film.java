package pg.AUI_lab3_film.film.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString()
@Entity
@Table(name = "films")
public class Film {
    @Id
    private String title;

    private int popularity;

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
