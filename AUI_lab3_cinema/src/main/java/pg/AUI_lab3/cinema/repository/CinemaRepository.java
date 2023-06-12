package pg.AUI_lab3.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pg.AUI_lab3.cinema.entity.Cinema;
import pg.AUI_lab3.film.entity.Film;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    List<Cinema> findAllByFilm(Film film);

    Optional<Cinema> findByFilmAndId(Film film, Long id);
}
