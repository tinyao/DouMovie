package im.ycz.doumovie.domain.model;

import java.io.Serializable;

/**
 * Created by tinyao on 15-9-5.
 */
public class Photo implements Serializable{

    private String id;

    private String alt;

    private String image;

    private String thumb;

    private String cover;


    public String getId() {
        return id;
    }

    public String getAlt() {
        return alt;
    }

    public String getImage() {
        return image;
    }

    public String getThumb() {
        return thumb;
    }

    public String getCover() {
        return cover;
    }
}
