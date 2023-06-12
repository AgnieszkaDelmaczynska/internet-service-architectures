package pg.AUI_lab1.character.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pg.AUI_lab1.character.entity.Film;
import pg.AUI_lab1.character.repository.FilmRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FilmService {
    private FilmRepository repository;

    @Autowired
    public FilmService(FilmRepository repository) {
        this.repository = repository;
    }

    public Optional<Film> find(String id) {
        return repository.find(id);
    }
    public void create(Film film) {
        repository.create(film);
    }

    public List<Film> findAll() {
        return repository.findAll();
    }

    public void delete (Film film) { repository.delete(film);}
}
