import org.Waleed.entities.Director;
import org.Waleed.repo.DirectorRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
public class TestDirectorRepo {
    @Autowired
    DirectorRepo directorRepo;

    @Test
    public void delete() {
        Assertions.assertAll(
                ()->Assertions.assertEquals(1, directorRepo.deleteDirector(2)),
                ()->Assertions.assertEquals(0, directorRepo.deleteDirector(5))
        );
    }

    @Test
    public void add() {
        Assertions.assertAll(
                ()->Assertions.assertEquals(1, directorRepo.createDirector(new Director(10, "David" , "Lynch", true))),
                ()->Assertions.assertThrows(DataIntegrityViolationException.class, () -> directorRepo.createDirector(new Director(1, "Christopher" , "Nolan", false)))
        );
    }

    @Test
    public void change() {
        Assertions.assertAll(
                ()->Assertions.assertEquals(1, directorRepo.changeStatus(1, false)),
                ()->Assertions.assertEquals(1, directorRepo.changeStatus(1, true))
        );
    }
}
