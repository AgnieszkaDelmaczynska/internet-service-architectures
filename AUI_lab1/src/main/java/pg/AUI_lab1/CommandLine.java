package pg.AUI_lab1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pg.AUI_lab1.character.service.CinemaService;
import pg.AUI_lab1.character.service.FilmService;

import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner {
    private CinemaService cinemaService;
    private FilmService filmService;
    public CommandLineService commandLineService;

    @Autowired
    public CommandLine(FilmService filmService, CinemaService cinemaService, CommandLineService commandLineService) {
        this.cinemaService = cinemaService;
        this.filmService = filmService;
        this.commandLineService = commandLineService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner keyboard = new Scanner(System.in);
        System.out.println();

        while (true)
        {
            String helloText = """
                    \nTo display data about films and cinemas press:\s
                    1: list all cinemas\s
                    2: list all films\s
                    3: add new cinema
                    4: add new film\s
                    5: delete existing cinema
                    6: delete existing film
                    7: quit the application""";
            System.out.println(helloText);
            String input = keyboard.nextLine();
            System.out.println(input);
            switch (input) {
                case "1" -> cinemaService.findAll().forEach(System.out::println);
                case "2" -> filmService.findAll().forEach(System.out::println);
                case "3" -> commandLineService.createCinema();
                case "4" -> commandLineService.createFilm();
                case "5" -> commandLineService.deleteCinema();
                case "6" -> commandLineService.deleteFilm();
                case "7" -> {
                    System.out.println("Goodbye, see you later!");
                    return;
                }
                default -> System.out.println("Unhandled input.");
            }
        }
    }
}
