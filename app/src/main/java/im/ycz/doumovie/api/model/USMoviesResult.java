package im.ycz.doumovie.api.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

import im.ycz.doumovie.domain.model.BoxMovie;
import im.ycz.doumovie.domain.model.Movie;
import im.ycz.doumovie.domain.model.RankedMovie;

/**
 * Created by tinyao on 15-9-4.
 */
public class USMoviesResult {

    private String date;

    private String title;

    @SerializedName("subjects")
    private List<BoxMovie> boxMovies;

    public String getTitle() {
        return title;
    }

    public List<BoxMovie> getMovies() {
        return boxMovies;
    }

    public String getDate() {
        return date;
    }



}
