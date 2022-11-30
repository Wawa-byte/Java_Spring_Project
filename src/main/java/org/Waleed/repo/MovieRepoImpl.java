package org.Waleed.repo;

import org.Waleed.entities.Director;
import org.Waleed.entities.Movie;
import org.Waleed.entities.Result;
import org.Waleed.repo.rowmapper.DirectorRowMapper;
import org.Waleed.repo.rowmapper.MovieRowMapper;
import org.Waleed.repo.rowmapper.ResultRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepoImpl implements MovieRepo {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int count() {
        String sql = "select count(*) from movie";
        Integer number =  namedParameterJdbcTemplate.getJdbcTemplate().queryForObject(sql, Integer.class);
        if (number != null) {
            return number;
        } else {
            return -1;
        }
    }

    @Override
    public List<Movie> getAll() {
        String sql = "select * from movie";
        return namedParameterJdbcTemplate.getJdbcTemplate().query(sql, new MovieRowMapper());
    }

    @Override
    public List<Result> findAll() {
        String sql = "select d.fName, m.title from movie m inner join director d on d.director_id = m.director_id";
        return namedParameterJdbcTemplate.getJdbcTemplate().query(sql, new ResultRowMapper());
    }

    @Override
    public boolean exists(int movieId) {
        String sql = "select count(*) from movie where movie_id = :movieId";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("movieId", movieId);
        Integer number = namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, Integer.class);
        return number != null && number == 1;
    }

    @Override
    public boolean existingMovie(String name) {
        String sql = "select count(*) from movie where title = :title";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("title", name);
        Integer number = namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, Integer.class);
        return number != null && number == 1;
    }

    @Override
    public int delete(int movieId) {
        String sql = "delete movie where movie_id = :movieId";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("movieId", movieId);
        return namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    @Override
    public List<Movie> findMoviesByDirector(int directorId) {
        String sql = "select * from movie where director_id = :directorId";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("directorId", directorId);
        return namedParameterJdbcTemplate.query(sql, sqlParameterSource, new MovieRowMapper());
    }

    @Override
    public Result findMoveWithDirector(int movieId) {
        String sql = "select d.fName, m.title from movie m inner join director d on d.director_id = m.director_id where m.movie_id= :movieId";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("movieId", movieId);
        return namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, new ResultRowMapper());
    }

    @Override
    public int createMovie(Movie newMovie) {
        String sql = "insert into movie values (:movie_id, :title, :yearReleased, :takings, :director_id)";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("movie_id", newMovie.getMovie_id())
                .addValue("title", newMovie.getName())
                .addValue("yearReleased", newMovie.getYearReleased())
                .addValue("takings", newMovie.getTakings())
                .addValue("director_id", newMovie.getDirector_id());

        return namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    @Override
    public int changeTaking(int id, int takings) {
        String sql = "update movie set takings = :takings where movie_id = :movieId";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("movieId", id)
                .addValue("takings", takings);
        return namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }
}
