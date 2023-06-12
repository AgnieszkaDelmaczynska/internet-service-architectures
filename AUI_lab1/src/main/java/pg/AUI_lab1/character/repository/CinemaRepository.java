package pg.AUI_lab1.character.repository;

import org.springframework.beans.factory.annotation.Autowired;
import pg.AUI_lab1.character.entity.Cinema;
import pg.AUI_lab1.datastore.DataStore;
import pg.AUI_lab1.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class CinemaRepository implements Repository<Cinema, Long> {

    private DataStore store;

    @Autowired
    public CinemaRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Cinema> find(Long id) {
        return store.findCinema(id);
    }

    @Override
    public List<Cinema> findAll() {
        return store.findAllCinemas();
    }

    @Override
    public void create(Cinema entity) {
        store.createCinema(entity);
    }

    @Override
    public void delete(Cinema entity) {
        store.deleteCinema(entity.getId());
    }

    //
    @Override
    public void update(Cinema entity) {
        store.updateCinema(entity);
    }
}
