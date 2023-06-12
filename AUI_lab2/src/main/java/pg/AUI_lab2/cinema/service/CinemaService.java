package pg.AUI_lab2.cinema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pg.AUI_lab2.cinema.entity.Cinema;
import pg.AUI_lab2.cinema.entity.Film;
import pg.AUI_lab2.cinema.repository.CinemaRepository;
import pg.AUI_lab2.cinema.repository.FilmRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaService {
    private CinemaRepository cinemaRepository;

    private FilmRepository filmRepository;

    @Autowired
    public CinemaService(CinemaRepository cinemaRepository, FilmRepository filmRepository) {
        this.cinemaRepository = cinemaRepository;
        this.filmRepository = filmRepository;
    }

    public Optional<Cinema> find(Long id) {
        return cinemaRepository.findById(id);
    }

    public Optional<Cinema> find(String title, Long id) {
        Optional<Film> film = filmRepository.findById(title);
        if (film.isPresent()) {
            return cinemaRepository.findByIdAndFilm(id, film.get());
        } else {
            return Optional.empty();
        }
    }
    public List<Cinema> findAll() {
        return cinemaRepository.findAll();
    }

    public List<Cinema> findAll(Film film) {
        return cinemaRepository.findAllByFilm(film);
    }

    @Transactional
    public Cinema create(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

    @Transactional
    public void update(Cinema cinema) {
        cinemaRepository.save(cinema);
    }

    @Transactional
    public void delete(Long cinema) {
        cinemaRepository.deleteById(cinema);
    }
}
