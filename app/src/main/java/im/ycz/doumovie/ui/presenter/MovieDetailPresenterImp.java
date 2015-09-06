package im.ycz.doumovie.ui.presenter;

import im.ycz.doumovie.api.DouMovieApi;
import im.ycz.doumovie.domain.model.Movie;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tinyao on 15-8-31.
 */
public class MovieDetailPresenterImp extends MovieDetailPresenter {

    @Override
    public void onMovieReceived(Movie movie) {
        loadMovieDetail(movie);
    }

    private void loadMovieDetail(Movie movie) {
        view.showLoading();
        Subscription subscription = DouMovieApi.getInstance().getMovie(movie.getId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Movie>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Movie movie) {
                        view.hideLoading();
                        view.renderMovie(movie);
                    }
                });
        addSubscription(subscription);
    }

    @Override
    public void initialize() {

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
