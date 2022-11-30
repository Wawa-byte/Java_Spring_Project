package org.Waleed.repo.rowmapper;

import org.Waleed.entities.Result;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultRowMapper implements RowMapper<Result> {
    @Override
    public Result mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new Result(resultSet.getString("director.fName"), resultSet.getString("movie.title"));
    }
}
