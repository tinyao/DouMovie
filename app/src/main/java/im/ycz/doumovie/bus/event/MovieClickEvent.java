package im.ycz.doumovie.bus.event;

import im.ycz.doumovie.domain.model.Movie;

/**
 * Created by tinyao on 15-8-31.
 */
public class MovieClickEvent {

    private Movie movie;

    public MovieClickEvent(Movie clickData) {
        this.movie = clickData;
    }

    public Movie getMovie() {
        return movie;
    }

}
