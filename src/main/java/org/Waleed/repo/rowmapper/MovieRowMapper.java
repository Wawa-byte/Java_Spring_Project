package org.Waleed.repo.rowmapper;

import org.Waleed.entities.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRowMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Movie movie = new Movie();
        movie.setMovie_id(resultSet.getInt("movie_id"));
        movie.setName(resultSet.getString("title"));
        movie.setYearReleased(resultSet.getInt("yearReleased"));
        movie.setTakings(resultSet.getInt("takings"));
        movie.setDirector_id(resultSet.getInt("director_id"));
        return movie;
    }
}
