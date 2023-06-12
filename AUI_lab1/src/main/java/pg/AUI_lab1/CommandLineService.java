package pg.AUI_lab1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pg.AUI_lab1.character.entity.Cinema;
import pg.AUI_lab1.character.entity.Film;
import pg.AUI_lab1.character.service.CinemaService;
import pg.AUI_lab1.character.service.FilmService;
import pg.AUI_lab1.datastore.DataStore;

import java.util.List;
import java.util.Scanner;
import java.util.Optional;

@Component
public class CommandLineService {

    CinemaService cinemaService;
    FilmService filmService;
    DataStore dataStore;

    @Autowired
    public CommandLineService ( CinemaService cinemaService,FilmService filmService, DataStore dataStore) {
        this.cinemaService = cinemaService;
        this.filmService = filmService;
        this.dataStore = dataStore;
    }

    public void deleteFilm() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter title: ");
        String title = scan.next();
        Film filmNull = Film.builder().build();
        Film film = filmService.find(title).get();
        filmService.delete(film);
        List<Cinema> realEstates = dataStore.findFilmInCinema(film);
        for(Cinema realEstate:realEstates) {
            realEstate.setBroadcastedFilms(filmNull);
        }
        System.out.println("Film deleted.");
    }

    public Film createFilm() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the title: ");
        String inputTitle = scan.next();
        System.out.println("Enter the category: ");
        String inputCategory = scan.next();
        System.out.println("Enter the precursor: ");
        String inputDirector = scan.next();
        System.out.println("Enter the production year: ");
        int inputProductionYear = scan.nextInt();
        Film inputFilm = Film.builder()
                .title(inputTitle)
                .category(inputCategory)
                .precursor(inputDirector)
                .popularity(inputProductionYear)
                .build();
        filmService.create(inputFilm);
        System.out.println("Film added.");
        return inputFilm;
    }

    public void createCinema() {
        Scanner scan = new Scanner(System.in);

//        System.out.println("Enter the ID of a cinema to be added (Long format): ");
//        Long inputID = scan.nextLong();

        System.out.print("Enter the name: ");
        String inputName = scan.next();

        System.out.println("Enter the year of establishment: ");
        int inputYearOfEstablishment = scan.nextInt();

        System.out.print("Enter film title name: ");
        String stringFilm = scan.next();

        Film film;
        Optional<Film> filmOptional = filmService.find(stringFilm);

        if (filmOptional.isEmpty()) {

            System.out.print("Enter category: ");
            String inputCategory = scan.next();

            System.out.print("Enter precursor: ");
            String inputPrecursor = scan.next();

            System.out.print("Enter polularity: ");
            int inputPopularity = scan.nextInt();

            film = Film.builder()
                    .title(stringFilm)
                    .category(inputCategory)
                    .precursor(inputPrecursor)
                    .popularity(inputPopularity)
                    .build();
            filmService.create(film);
        }
        else {
            film = filmOptional.get();
        }
        Cinema cinema = Cinema.builder()
                .name(inputName)
                .yearOfEstablishment(inputYearOfEstablishment)
                .broadcastedFilms(film)
                .build();
        cinemaService.create(cinema);
        System.out.println("Cinema created.");
    }

    public void deleteCinema() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the ID of a cinema to be deleted (Long format): ");
        String deleteCinema = scan.nextLine();
        cinemaService.delete(Integer.parseInt(deleteCinema));
        System.out.println("Cinema deleted.");
    }
}
