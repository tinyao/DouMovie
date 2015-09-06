package im.ycz.doumovie.domain.model;

import com.google.gson.annotations.SerializedName;

import im.ycz.doumovie.domain.model.Movie;

/**
 * Created by tinyao on 15-9-4.
 */
public class RankedMovie {

    private int delta;

    private int rank;

    @SerializedName("subject")
    private Movie movie;

    public int getDelta() {
        return delta;
    }

    public int getRank() {
        return rank;
    }

    public Movie getMovie() {
        return movie;
    }
}
