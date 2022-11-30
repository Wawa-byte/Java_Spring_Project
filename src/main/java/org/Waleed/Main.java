package org.Waleed;

import org.Waleed.entities.Director;
import org.Waleed.entities.Movie;
import org.Waleed.repo.DirectorRepo;
import org.Waleed.repo.DirectorRepoImpl;
import org.Waleed.repo.MovieRepo;
import org.Waleed.repo.MovieRepoImpl;
import org.Waleed.service.DirectorService;
import org.Waleed.service.DirectorServiceImpl;
import org.Waleed.service.MovieService;
import org.Waleed.service.MovieServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        DirectorRepo directorRepo = context.getBean(DirectorRepoImpl.class);
        DirectorService directorService = context.getBean(DirectorServiceImpl.class);
        MovieRepo movieRepo = context.getBean(MovieRepoImpl.class);
        MovieService movieService = context.getBean(MovieServiceImpl.class);

        System.out.println("How many directors in database: " + directorRepo.count());
        System.out.println("How many movies in database: " + movieRepo.count());

        System.out.println("\nList of directors:");
        directorRepo.getAll().forEach(System.out::println);

        System.out.println("\nList of movies:");
        movieRepo.getAll().forEach(System.out::println);

        System.out.println("\nFinding director with using findByID:");
        System.out.println(directorRepo.findById(2));

        // Finding a director with good data
        System.out.println("\nFinding a director with FindADirector (Correct ID): ");
        directorService.findADirector(3).ifPresentOrElse(System.out::println, () -> System.out.println("Not a recognised id"));

        // Finding a director but the id does not exist
        System.out.println("\nFinding a director with FindADirector (Bad ID): ");
        directorService.findADirector(22).ifPresentOrElse(System.out::println, () -> System.out.println("Not a recognised id."));

        // Adding a director
        System.out.println("\nAdding a director: " + directorService.addDirector(new Director(4, "Martin" , "Scorsese", true)));
        System.out.println("Adding a director (fail ID): " + directorService.addDirector(new Director(1, "Federico" , "Fellini", false)));
        System.out.println("Adding a director (fail Name): " + directorService.addDirector(new Director(5, "Waleed" , "Akhtar", true)));

        // Delete a director
        System.out.println("\nDeleting a existing director: " + directorService.deleteDirector(2));
        System.out.println("Deleting a none existing director: " + directorService.deleteDirector(5));

        // Update a director status
        System.out.println("\nUpdate a director to be retired: " + directorService.changeDirectorStatus(1, false));

        // Find a movie with ID
        movieService.findMoveWithDirector(1).ifPresentOrElse(System.out::println, () -> System.out.println("Not a valid ID"));
        movieService.findMoveWithDirector(5).ifPresentOrElse(System.out::println, () -> System.out.println("Not a valid ID"));

        // Deleting a movie
        System.out.println("\nDeleting a existing movie: " + movieService.delete(3));
        System.out.println("Deleting a none existing movie: " + movieService.delete(5));

        // Adding a movie
        System.out.println("\nAdding a movie: " + movieService.addMovie(new Movie(4, "The Gray Man", 2016, 14, 1)));
        System.out.println("Adding a movie (fail ID): " + movieService.addMovie(new Movie(4, "Bullet Train", 2001, 63, 3)));
        System.out.println("Adding a movie (fail Name): " + movieService.addMovie(new Movie(5, "The Gray Man", 1996, 74, 4)));

        // Find movies via director
        System.out.println("\nFinding a all the movies made by a director: " + movieService.findMoviesByDirector(1));
        System.out.println("Finding a all the movies made by a director (Empty): " + movieService.findMoviesByDirector(2));

        // Modify a movie revenue
        System.out.println("\nModify a movies to be takings: " + movieService.changeMovieTaking(1, 100));

        // Average income of a director
        System.out.println("\nAverage Income of director: " + directorRepo.aveIncome(1));

        // Inactive Directors
        System.out.println("\nInactive Directors: " + directorRepo.inactiveDirector());
    }
}