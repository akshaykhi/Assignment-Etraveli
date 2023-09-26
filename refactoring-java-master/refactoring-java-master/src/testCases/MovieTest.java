package testCases;

import org.junit.Test;
import pojo.Movie;


import static org.junit.Assert.*;

public class MovieTest {

     Movie movie = new Movie("Avengers", "regular");

    @Test
    public void testGetMovieTitle() {
        assertEquals("Avengers", movie.getMovieTitle());
    }

    @Test
    public void testGetMovieCode() {
        assertEquals("regular", movie.getMovieCode());
    }
}