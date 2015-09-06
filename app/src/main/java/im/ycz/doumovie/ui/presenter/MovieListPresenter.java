package im.ycz.doumovie.ui.presenter;

import android.content.Context;

import im.ycz.doumovie.domain.model.Movie;
import im.ycz.doumovie.ui.view.MovieListView;

/**
 * Created by tinyao on 15-8-31.
 */
public abstract class MovieListPresenter extends Presenter<MovieListView> {

    public abstract void getMovies();

    public abstract void onMovieClicked(Context context, Movie movie);

    public abstract boolean isLoaded();
}
