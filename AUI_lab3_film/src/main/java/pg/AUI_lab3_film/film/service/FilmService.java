package pg.AUI_lab3_film.film.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pg.AUI_lab3_film.film.entity.Film;
import pg.AUI_lab3_film.film.event.repository.FilmEventRepository;
import pg.AUI_lab3_film.film.repository.FilmRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FilmService {

    private FilmRepository repository;

    private FilmEventRepository eventRepository;

    @Autowired
    public FilmService(FilmRepository repository, FilmEventRepository eventRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
    }

    public Optional<Film> find(String title) {
        return repository.findById(title);
    }

    @Transactional
    public void create(Film film) {
        repository.save(film);
        eventRepository.create(film); //najpierw cinema projekt puścić
    }

    public List<Film> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void delete(Film film) {
        eventRepository.delete(film);
        repository.delete(film);
    }

    @Transactional
    public void update(Film film) {
        //eventRepository.update(film);
        repository.save(film);
    }
}
