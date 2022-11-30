package org.Waleed.repo;

import org.Waleed.entities.Director;
import org.Waleed.repo.rowmapper.DirectorRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DirectorRepoImpl implements DirectorRepo {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int count() {
        String sql = "select count(*) from director";
        Integer number =  namedParameterJdbcTemplate.getJdbcTemplate().queryForObject(sql, Integer.class);
        if (number != null) {
            return number;
        } else {
            return -1;
        }
    }

    @Override
    public List<Director> getAll() {
        String sql = "select * from director";
        return namedParameterJdbcTemplate.getJdbcTemplate().query(sql, new DirectorRowMapper());
    }

    @Override
    public Director findById(int id) {
        String sql = "select * from director where director_id = :directorId";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("directorId", id);
        return namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, new DirectorRowMapper());
    }

    @Override
    public boolean exists(int id){
        String sql = "select count(*) from director where director_id = :directorId";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("directorId", id);
        Integer number = namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, Integer.class);
        return number != null && number == 1;
    }

    @Override
    public int deleteDirector(int id) {
        String sql = "delete from director where director_id = :directorId";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("directorId", id);
        return namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    @Override
    public boolean existingDirector(String name) {
        String sql = "select count(*) from director where fName = :fName";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("fName", name);
        Integer number = namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, Integer.class);
        return number != null && number == 1;
    }

    @Override
    public int createDirector(Director newDirector) {
        String sql = "insert into director values (:id, :fName, :lName, :stillActive)";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", newDirector.getDirectorId())
                .addValue("fName", newDirector.getFName())
                .addValue("lName", newDirector.getLName())
                .addValue("stillActive", newDirector.isStillActive());

        return namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    @Override
    public int changeStatus(int id, boolean newStatus) {
        String sql = "update director set stillActive = :newStatus where director_id = :directorId";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("directorId", id)
                .addValue("newStatus", newStatus);
        return namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    @Override
    public int aveIncome(int id){
        String sql = "select avg(takings) from movie where director_id = :director_id";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("director_id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, Integer.class);
    }

    @Override
    public int inactiveDirector() {
        String sql = "Select count(director_id) from director where stillActive = false ";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, Integer.class);
    }

//    @Override
//    public String grossingFilm(){
//        String sql = "Select max(takings) from movie";
//    }
}
