package org.Waleed.service;

import lombok.extern.slf4j.Slf4j;
import org.Waleed.entities.Director;
import org.Waleed.repo.DirectorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DirectorServiceImpl implements DirectorService {

    @Autowired
    DirectorRepo directorRepo;

    @Override
    public int count(){
        return directorRepo.count();
    }

    @Override
    public List<Director> findAll() {
        return directorRepo.getAll();
    }

    @Override
    public Optional<Director> findADirector(int id) {
        if (directorRepo.exists(id)) {
            return Optional.of(directorRepo.findById(id));
        }
        log.warn("Tried to find a Director ID which doesn't exist");
        return Optional.empty();
    }

    @Override
    public boolean deleteDirector(int id) {
        if (directorRepo.exists(id)) {
            return directorRepo.deleteDirector(id) == 1;
        }
        log.error("Could not delete a director with id " + id + " because it does not exist");
        return false;
    }

    @Override
    public boolean addDirector(Director newDirector) {
        if (directorRepo.existingDirector(newDirector.getFName())){
            log.error("Could not add a director because a director with the name " + newDirector.getFName() + " already exists");
            return false;
        }
        if (directorRepo.exists(newDirector.getDirectorId())) {
            log.error("Could not add a director because a director with the id " + newDirector.getDirectorId() + " already exists");
            return false;
        }
        return directorRepo.createDirector(newDirector) == 1;
    }

    @Override
    public boolean changeDirectorStatus(int id, boolean newStatus) {
        return directorRepo.changeStatus(id, newStatus) == 1;
    }
}
