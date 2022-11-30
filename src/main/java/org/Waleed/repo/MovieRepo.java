package org.Waleed.repo;

import org.Waleed.entities.Director;
import org.Waleed.entities.Movie;
import org.Waleed.entities.Result;

import java.util.List;

public interface MovieRepo {

    int count();

    List<Movie> getAll();

    List<Result> findAll();

    boolean exists(int movieId);

    boolean existingMovie(String name);

    int delete(int movieId);

    List<Movie> findMoviesByDirector(int directorId);

    Result findMoveWithDirector(int movieId);

    int createMovie(Movie newMovie);

    int changeTaking(int id, int taking);
}
