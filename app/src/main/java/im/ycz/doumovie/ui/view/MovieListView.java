package im.ycz.doumovie.ui.view;

import java.util.List;
import im.ycz.doumovie.domain.model.Movie;

/**
 * Created by tinyao on 15-8-31.
 */
public interface MovieListView extends View{

    public void showLoading();

    public void hideLoading();

    public void renderMovies(final List<Movie> movies);

}
