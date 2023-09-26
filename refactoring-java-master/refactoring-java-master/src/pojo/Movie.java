package pojo;

public class Movie {
    private String movieTitle;
    private String movieCode;

    public Movie(String title, String code) {

        movieTitle = title;
        movieCode = code;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getMovieCode() {
        return movieCode;
    }
}
