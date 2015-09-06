package im.ycz.doumovie.api;

import im.ycz.doumovie.api.model.ComingMoviesResult;
import im.ycz.doumovie.api.model.IntheaterMoviesResult;
import im.ycz.doumovie.api.model.MovieListResult;
import im.ycz.doumovie.api.model.NewMoviesResult;
import im.ycz.doumovie.api.model.Top250Results;
import im.ycz.doumovie.api.model.USMoviesResult;
import im.ycz.doumovie.api.model.WeeklyMoviesResult;
import im.ycz.doumovie.domain.model.Movie;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by tinyao on 15-8-31.
 */
public interface DouMovieService {

    @GET("/in_theaters")
    Observable<IntheaterMoviesResult> getIntheaterMovies(@Query("city") String city);

    @GET("/coming_soon")
    Observable<ComingMoviesResult> getComingMovies(@Query("start") int start, @Query("count") int count);

    @GET("/weekly")
    Observable<WeeklyMoviesResult> getWeeklyMovies();

    @GET("/us_box")
    Observable<USMoviesResult> getUSMovies();

    @GET("/new_movies")
    Observable<NewMoviesResult> getNewMovies();

    @GET("/top250")
    Observable<Top250Results> getTop250Movies();

    @GET("/subject/{id}")
    Observable<Movie> getMovie(@Path("id") String id);

}
