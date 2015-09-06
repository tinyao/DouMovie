package im.ycz.doumovie.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import im.ycz.doumovie.domain.model.Movie;

/**
 * Created by tinyao on 15-8-31.
 */
public class IntheaterMoviesResult extends MovieListResult{

    private int count;

    private int start;

    private int total;

    public int getCount() {
        return count;
    }

    public int getStart() {
        return start;
    }

    public int getTotal() {
        return total;
    }

}
