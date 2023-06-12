package pg.AUI_lab2.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pg.AUI_lab2.cinema.entity.Cinema;
import org.springframework.stereotype.Repository;
import pg.AUI_lab2.cinema.entity.Film;

import java.util.List;
import java.util.Optional;


@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    Optional<Cinema> findByIdAndFilm(Long id, Film film);
    List<Cinema> findAllByFilm(Film film);
}
