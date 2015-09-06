package im.ycz.doumovie.ui.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import im.ycz.doumovie.api.DouMovieApi;
import im.ycz.doumovie.api.MovieType;
import im.ycz.doumovie.domain.model.Movie;
import im.ycz.doumovie.ui.activity.MovieDetailActivity;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tinyao on 15-8-31.
 */
public class MovieListPresenterImp extends MovieListPresenter{

    private List<Movie> movies;

    private MovieType type;

    public MovieListPresenterImp(MovieType type) {
        this.type = type;
    }

    @Override
    public void getMovies() {
        view.showLoading();
        Subscription subscription = getSpecificMovies(type)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Movie>>() {
                    @Override
                    public void onCompleted() {
                        view.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("DEBUG", e.toString());
                    }

                    @Override
                    public void onNext(List<Movie> movies) {
                        view.hideLoading();
                        setMovies(movies);
                        showMovies();
                    }
                });
        addSubscription(subscription);
    }

    private Observable<List<Movie>> getSpecificMovies(MovieType type) {
        switch (type) {
            case INTHEATRE:
                return DouMovieApi.getInstance().getIntheaterMovies("北京");
            case COMING:
                return DouMovieApi.getInstance().getComingMovies(0, 20);
            case TOP250:
                return DouMovieApi.getInstance().getTop250();
            case WEEKLY:
                return DouMovieApi.getInstance().getWeeklyHot();
            case US:
                return DouMovieApi.getInstance().getUSList();
            case NEWSHOW:
                return DouMovieApi.getInstance().getNewMovies();
        }
        return DouMovieApi.getInstance().getIntheaterMovies("北京");
    }

    @Override
    public void onMovieClicked(Context context, Movie data) {
        Intent detailIntent = new Intent(context, MovieDetailActivity.class);
        detailIntent.putExtra("movie", data);
        context.startActivity(detailIntent);
    }

    private void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    private void showMovies() {
        view.renderMovies(movies);
    }

    @Override
    public void initialize() {
        Log.d("DEBUG", "getMovie");
        getMovies();
    }

    @Override
    public boolean isLoaded() {
        return movies != null;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }
}
