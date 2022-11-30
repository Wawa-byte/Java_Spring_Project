package org.Waleed.service;

import org.Waleed.entities.Director;

import java.util.List;
import java.util.Optional;

public interface DirectorService {
    int count();

    List<Director> findAll();

    Optional<Director> findADirector(int id);

    boolean deleteDirector(int id);

    boolean addDirector(Director newDirector);

    boolean changeDirectorStatus(int id, boolean newStatus);
}
