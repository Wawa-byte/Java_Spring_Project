package org.Waleed.repo;

import org.Waleed.entities.Director;

import java.util.List;

public interface DirectorRepo {
    int count();
    List<Director> getAll();

    Director findById(int id);

    boolean exists(int id);

    int deleteDirector(int id);

    boolean existingDirector(String name);

    int createDirector(Director newDirector);

    int changeStatus(int id, boolean newStatus);

    int aveIncome(int id);

    int inactiveDirector();

    //String grossingFilm();
}
