package im.ycz.doumovie.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tinyao on 15-9-4.
 */
public class BoxMovie {

    private int rank;

    private int box;

    @SerializedName("new")
    private boolean isNew;

    @SerializedName("subject")
    private Movie movie;

    public int getRank() {
        return rank;
    }

    public int getBox() {
        return box;
    }

    public boolean getIsNew() {
        return isNew;
    }

    public Movie getMovie() {
        return movie;
    }
}
