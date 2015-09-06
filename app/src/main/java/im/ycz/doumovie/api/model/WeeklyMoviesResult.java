package im.ycz.doumovie.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import im.ycz.doumovie.domain.model.Movie;
import im.ycz.doumovie.domain.model.RankedMovie;

/**
 * Created by tinyao on 15-9-4.
 */
public class WeeklyMoviesResult {

    private String title;

    @SerializedName("subjects")
    private List<RankedMovie> rankedMovies;

    public String getTitle() {
        return title;
    }

    public List<RankedMovie> getMovies() {
        return rankedMovies;
    }

}
