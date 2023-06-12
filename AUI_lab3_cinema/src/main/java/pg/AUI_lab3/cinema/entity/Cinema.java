package pg.AUI_lab3.cinema.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import pg.AUI_lab3.film.entity.Film;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString()
@Entity
@Table(name = "cinemas")
public class Cinema implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int yearOfEstablishment;

    @ManyToOne()
    @JoinColumn(name = "films")
    private Film film;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cinema cinema = (Cinema) o;
        return id != null && Objects.equals(id, cinema.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
