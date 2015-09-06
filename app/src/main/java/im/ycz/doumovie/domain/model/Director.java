package im.ycz.doumovie.domain.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tinyao on 15-9-1.
 */
public class Director extends Cast implements Serializable {

    @Override
    public boolean isDirector() {
        return true;
    }
}
