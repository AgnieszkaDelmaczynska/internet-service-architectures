package pg.AUI_lab1.datastore;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import pg.AUI_lab1.character.entity.Film;
import pg.AUI_lab1.character.entity.Cinema;
import pg.AUI_lab1.serialization.CloningUtility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Log
@Component
public class DataStore {

    private Set<Film> films = new HashSet<>();

    public Set<Cinema> cinemas = new HashSet<>();

    public synchronized List<Film> findAllFilms() {
    return new ArrayList<>(films);
}

    public Optional<Film> findFilm (String title) {
        return films.stream()
                .filter(type -> type.getTitle().equals(title))
                .findFirst();
    }

    public synchronized void createFilm(Film film) throws IllegalArgumentException {
        findFilm(film.getTitle()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The film name \"%s\" is not unique", film.getTitle()));
                },
                () -> films.add(CloningUtility.clone(film)));
    }

    public synchronized void deleteFilm(String title) throws IllegalArgumentException {
        findFilm(title).ifPresentOrElse(
                original -> films.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The film with title \"%d\" does not exist", title));
                });
    }

    public synchronized void updateFilm(Film entity) throws IllegalArgumentException {
        findCinema(Long.valueOf(entity.getTitle())).ifPresentOrElse(
                original -> {
                    films.remove(original);
                    films.add(entity);
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("A film with id \"%d\" does not exist", entity.getTitle()));
                });
    }

    public synchronized List<Cinema> findAllCinemas() {
        return cinemas.stream()
                .map(CloningUtility::clone)
                .collect(Collectors.toList());
    }

    public Optional<Cinema> findCinema(Long id) {
        return cinemas.stream()
                .filter(cinema -> cinema.getId().equals(id))
                .findFirst();
                //.map(CloningUtility::clone);
    }

    public synchronized void createCinema(Cinema cinema) throws IllegalArgumentException {
        cinema.setId(findAllCinemas().stream()
                .mapToLong(Cinema::getId)
                .max().orElse(0) + 1);
        cinemas.add(CloningUtility.clone(cinema));
    }

    //
    public synchronized void updateCinema(Cinema cinema) throws IllegalArgumentException {
        findCinema(cinema.getId()).ifPresentOrElse(
                original -> {
                    cinemas.remove(original);
                    cinemas.add(CloningUtility.clone(cinema));
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The cinema with id \"%d\" does not exist", cinema.getId()));
                });
    }

    public synchronized void deleteCinema(Long id) throws IllegalArgumentException {
        findCinema(id).ifPresentOrElse(
                original -> cinemas.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The cinema with id \"%d\" does not exist", id));
                });
    }

    public List<Cinema> findFilmInCinema (Film film) {
        return cinemas.stream()
                .filter(realEstate -> realEstate.getBroadcastedFilms().equals(film))
                .collect(Collectors.toList());
    }
}
