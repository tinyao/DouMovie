package im.ycz.doumovie.domain.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tinyao on 15-9-1.
 */
public class Cast implements Serializable {

    private boolean isDirector = false;

    private String id;

    private String name;

    @SerializedName("alt")
    private String url;

    @SerializedName("avatars")
    private Image avatar;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public Image getAvatar() {
        return avatar;
    }

    @Override
    public String toString() {
        return "Cast{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", avatar=" + avatar +
                '}';
    }

    public boolean isDirector() {
        return false;
    }
}
