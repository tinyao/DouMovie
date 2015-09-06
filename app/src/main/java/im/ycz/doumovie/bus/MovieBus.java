package im.ycz.doumovie.bus;

import com.squareup.otto.Bus;

/**
 * Created by tinyao on 15-8-31.
 */
public class MovieBus extends Bus{

    private static MovieBus movieBus;

    public static MovieBus getBus() {
        if (movieBus == null) {
            movieBus = new MovieBus();
        }
        return movieBus;
    }

}
