package org.Waleed.service;

import org.Waleed.entities.Movie;
import org.Waleed.entities.Result;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    List<Result> findAll();

    boolean delete(int movieId);

    Optional<List<Movie>> findMoviesByDirector(int directorId);

    boolean addMovie(Movie newMovie);

    Optional<Result> findMoveWithDirector(int movieId);

    boolean changeMovieTaking(int id, int taking);
}
