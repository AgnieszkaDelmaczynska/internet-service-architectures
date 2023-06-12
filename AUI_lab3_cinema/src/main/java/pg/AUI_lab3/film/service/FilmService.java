package pg.AUI_lab3.film.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pg.AUI_lab3.film.entity.Film;
import pg.AUI_lab3.film.repository.FilmRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FilmService {

    private final FilmRepository repository;

    @Autowired
    public FilmService(FilmRepository repository) {
        this.repository = repository;
    }


    public Optional<Film> find(String title) {
        return repository.findById(title);
    }

    public List<Film> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void create(Film film) {
        repository.save(film);
    }

    @Transactional
    public void delete(String title) {
        repository.deleteById(title);
    }

    @Transactional
    public void update(Film film) {
        repository.save(film);
    }
}
