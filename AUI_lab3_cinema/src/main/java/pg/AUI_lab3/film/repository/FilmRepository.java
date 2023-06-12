package pg.AUI_lab3.film.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pg.AUI_lab3.film.entity.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, String>{
}
