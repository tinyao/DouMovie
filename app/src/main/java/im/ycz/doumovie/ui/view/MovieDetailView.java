package im.ycz.doumovie.ui.view;

import im.ycz.doumovie.domain.model.Movie;

/**
 * Created by tinyao on 15-8-31.
 */
public interface MovieDetailView extends View {

    public void showLoading();

    public void hideLoading();

    public void renderMovie(Movie movie);

}
