package im.ycz.doumovie.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import im.ycz.doumovie.api.model.ComingMoviesResult;
import im.ycz.doumovie.api.model.IntheaterMoviesResult;
import im.ycz.doumovie.api.model.MovieListResult;
import im.ycz.doumovie.api.model.NewMoviesResult;
import im.ycz.doumovie.api.model.USMoviesResult;
import im.ycz.doumovie.api.model.WeeklyMoviesResult;
import im.ycz.doumovie.domain.model.BoxMovie;
import im.ycz.doumovie.domain.model.Movie;
import im.ycz.doumovie.domain.model.RankedMovie;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by tinyao on 15-8-31.
 */
public class DouMovieApi {

    public static final String BASE_URL = "https://api.douban.com/v2/movie";
    public static final String API_KEY = "0b2bdeda43b5688921839c8ecb20399b";

    private DouMovieService movieService;

    public static final DouMovieApi instance = new DouMovieApi();

    public static DouMovieApi getInstance() {
        return instance;
    }

    public DouMovieApi(){
        initRestAdapter();
    }

    private void initRestAdapter() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addQueryParam("apikey", API_KEY);
                    }
                })
                .build();

        movieService = adapter.create(DouMovieService.class);
    }

    private Func1 funMovieList = new Func1<MovieListResult, List<Movie>>() {
        @Override
        public List<Movie> call(MovieListResult result) {
            return result.getMovies();
        }
    };

    /**
     * 正在热映的电影
     * @param city
     * @return
     */
    public Observable<List<Movie>> getIntheaterMovies(String city) {
        return movieService.getIntheaterMovies(city)
            .map(funMovieList);
    }

    public Observable<List<Movie>> getComingMovies(int start, int count) {
        return movieService.getComingMovies(start, count)
                .map(funMovieList);
    }

    public Observable<List<Movie>> getNewMovies() {
        return movieService.getNewMovies().map(funMovieList);
    }

    public Observable<List<Movie>> getTop250() {
        return movieService.getTop250Movies().map(funMovieList);
    }

    public Observable<List<Movie>> getUSList() {
        return movieService.getUSMovies().map(new Func1<USMoviesResult, List<Movie>>() {
            @Override
            public List<Movie> call(USMoviesResult usMoviesResult) {
                List<Movie> movies = new ArrayList<Movie>();
                List<BoxMovie> boxMovies = usMoviesResult.getMovies();
                for (BoxMovie bmovie : boxMovies) {
                    movies.add(bmovie.getMovie());
                }
                return movies;
            }
        });
    }

    public Observable<List<Movie>> getWeeklyHot() {
        return movieService.getWeeklyMovies().map(new Func1<WeeklyMoviesResult, List<Movie>>() {
            @Override
            public List<Movie> call(WeeklyMoviesResult weeklyMoviesResult) {
                List<Movie> movies = new ArrayList<Movie>();
                List<RankedMovie> rankedMovies = weeklyMoviesResult.getMovies();
                for (RankedMovie rmovie : rankedMovies) {
                    movies.add(rmovie.getMovie());
                }
                return movies;
            }
        });
    }

    /**
     * 通过id获取电影详细信息
     * @param id
     * @return
     */
    public Observable<Movie> getMovie(String id) {
        return movieService.getMovie(id);
    }



}
