package im.ycz.doumovie.api;

/**
 * Created by tinyao on 15-9-4.
 */
public enum MovieType {
    INTHEATRE(0), COMING(1), NEWSHOW(2), WEEKLY(3), US(4), TOP250(5);

    int value;

    MovieType(int value) {
        this.value = value;
    }
}
