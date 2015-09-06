package im.ycz.doumovie.domain.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tinyao on 15-8-31.
 */
public class Rating implements Serializable {

    @SerializedName("average")
    private float average;

    @SerializedName("max")
    private int max;

    @SerializedName("min")
    private int min;

    @SerializedName("stars")
    private String stars;

    public float getAverage() {
        return average;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public String getStars() {
        return stars;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "average=" + average +
                ", max=" + max +
                ", min=" + min +
                ", stars='" + stars + '\'' +
                '}';
    }
}
