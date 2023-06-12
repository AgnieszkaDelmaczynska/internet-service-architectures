package pg.AUI_lab1.character.repository;

import org.springframework.beans.factory.annotation.Autowired;
import pg.AUI_lab1.character.entity.Film;
import pg.AUI_lab1.repository.Repository;
import pg.AUI_lab1.datastore.DataStore;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class FilmRepository implements Repository<Film, String> {

    private DataStore store;

    @Autowired
    public FilmRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public void create(Film entity) {
        store.createFilm(entity);
    }

    @Override
    public void delete(Film entity) { store.deleteFilm(entity.getTitle());}

    @Override
    public void update(Film entity) {
        store.updateFilm(entity);
    }

    @Override
    public Optional<Film> find(String id) {
        return store.findFilm(id);
    }

    @Override
    public List<Film> findAll() {
        return store.findAllFilms();
    }
}
