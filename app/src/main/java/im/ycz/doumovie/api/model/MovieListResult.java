package im.ycz.doumovie.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import im.ycz.doumovie.domain.model.Movie;

/**
 * Created by tinyao on 15-9-4.
 */
public class MovieListResult {

    private String title;

    @SerializedName("subjects")
    private List<Movie> movies;

    public String getTitle() {
        return title;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
