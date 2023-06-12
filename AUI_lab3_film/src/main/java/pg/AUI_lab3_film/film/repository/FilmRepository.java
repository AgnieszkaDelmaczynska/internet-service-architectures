package pg.AUI_lab3_film.film.repository;

import pg.AUI_lab3_film.film.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface FilmRepository extends JpaRepository<Film, String> {
}
