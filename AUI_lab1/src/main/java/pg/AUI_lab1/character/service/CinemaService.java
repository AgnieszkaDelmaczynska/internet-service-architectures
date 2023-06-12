package pg.AUI_lab1.character.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pg.AUI_lab1.character.entity.Cinema;
import pg.AUI_lab1.character.repository.CinemaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaService {
    private CinemaRepository repository;

    @Autowired
    public CinemaService(CinemaRepository repository) {
        this.repository = repository;
    }

    public Optional<Cinema> find(Long id) {
        return repository.find(id);
    }

    public List<Cinema> findAll() {
        return repository.findAll();
    }

    public void create(Cinema cinema) {
        repository.create(cinema);
    }

    public void update(Cinema cinema) {
        repository.update(cinema);
    }

    public void delete(int cinemaID) {
        repository.delete(repository.find((long) cinemaID).orElseThrow());
    }
}
