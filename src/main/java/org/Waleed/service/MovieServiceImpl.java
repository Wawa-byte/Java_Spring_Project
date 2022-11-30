package org.Waleed.service;

import lombok.extern.slf4j.Slf4j;
import org.Waleed.entities.Director;
import org.Waleed.entities.Movie;
import org.Waleed.entities.Result;
import org.Waleed.repo.DirectorRepo;
import org.Waleed.repo.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private DirectorRepo directorRepo;

    @Override
    public List<Result> findAll() {
       return movieRepo.findAll();
    }

    @Override
    public boolean delete(int movieId) {
        if (movieRepo.exists(movieId))
            return movieRepo.delete(movieId) == 1;
        return false;
    }

    @Override
    public Optional<List<Movie>> findMoviesByDirector(int directorId) {
        if (directorRepo.exists(directorId)) {
            List<Movie> movies = movieRepo.findMoviesByDirector(directorId);
            return movies.isEmpty()? Optional.empty(): Optional.of(movies);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Result> findMoveWithDirector(int movieId) {
        if (movieRepo.exists(movieId)) {
            return Optional.of(movieRepo.findMoveWithDirector(movieId));
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public boolean addMovie(Movie newMovie) {
        if (movieRepo.existingMovie(newMovie.getName())){
            log.error("Could not add a movie because a movie with the name " + newMovie.getName() + " already exists");
            return false;
        }
        if (movieRepo.exists(newMovie.getMovie_id())) {
            log.error("Could not add a movie because a movie with the id " + newMovie.getMovie_id() + " already exists");
            return false;
        }
        return movieRepo.createMovie(newMovie) == 1;
    }

    @Override
    public boolean changeMovieTaking(int id, int taking) {
        return movieRepo.changeTaking(id, taking) == 1;
    }
}
