package im.ycz.doumovie.domain.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tinyao on 15-8-31.
 */
public class Image implements Serializable{

    @SerializedName("large")
    private String large;
    @SerializedName("medium")
    private String medium;
    @SerializedName("small")
    private String small;

    public String getLarge() {
        return large;
    }

    public String getMedium() {
        return medium;
    }

    public String getSmall() {
        return small;
    }

    @Override
    public String toString() {
        return "Image{" +
                "large='" + large + '\'' +
                ", medium='" + medium + '\'' +
                ", small='" + small + '\'' +
                '}';
    }
}
