package im.ycz.doumovie.ui.presenter;

import im.ycz.doumovie.domain.model.Movie;
import im.ycz.doumovie.ui.view.MovieDetailView;

/**
 * Created by tinyao on 15-8-31.
 */
public abstract class MovieDetailPresenter extends Presenter<MovieDetailView> {

    public abstract void onMovieReceived(Movie movie);

}
