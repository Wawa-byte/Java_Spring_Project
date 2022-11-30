import org.Waleed.entities.Movie;
import org.Waleed.service.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
public class TestMovieService {

    @Autowired
    private MovieService movieService;

    @Test
    public void add() {
        Assertions.assertAll(
                ()->Assertions.assertEquals(true, movieService.addMovie(new Movie(10, "Black Butler", 2015, 85, 1))),
                ()->Assertions.assertEquals(false, movieService.addMovie(new Movie(1, "The Gray Man", 2016, 14, 1)))
        );
    }

    @Test
    public void find() {
        Assertions.assertAll(
                ()->Assertions.assertInstanceOf(Optional.class, movieService.findMoviesByDirector(1)),
                ()->Assertions.assertEquals(Optional.empty(), movieService.findMoviesByDirector(9))
        );
    }

    @Test
    public void modify() {
        Assertions.assertAll(
                ()->Assertions.assertEquals(true, movieService.changeMovieTaking(1, 43)),
                ()->Assertions.assertEquals(false, movieService.changeMovieTaking(17, 22))
        );
    }
}
