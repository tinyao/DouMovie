package im.ycz.doumovie.ui.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import im.ycz.doumovie.R;
import im.ycz.doumovie.api.MovieType;
import im.ycz.doumovie.bus.MovieBus;
import im.ycz.doumovie.bus.event.MovieClickEvent;
import im.ycz.doumovie.domain.model.Movie;
import im.ycz.doumovie.ui.adapter.MovieListAdapter;
import im.ycz.doumovie.ui.presenter.MovieListPresenter;
import im.ycz.doumovie.ui.presenter.MovieListPresenterImp;
import im.ycz.doumovie.ui.view.MovieListView;
import im.ycz.doumovie.utils.anim.AnimUtils;
import im.ycz.doumovie.utils.view.GridSpacingItemDecoration;

public class MovieListFragment extends BaseFragment implements MovieListView{

    private static final String TAG = BaseFragment.class.getName();

    private MovieListPresenter movieListPresenter;
    @Bind(R.id.lst_movies)
    protected RecyclerView movieListView;
    @Bind(R.id.loading)
    protected ProgressBar loadingView;
    private MovieListAdapter movieListAdapter;

    private int mType;

    private boolean isFragmentCreated = false;
    private boolean isVisibleToUser = false;

    public MovieListFragment() {

    }

    public static MovieListFragment newInstance(MovieType type) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle args = new Bundle();
        args.putInt("type", type.ordinal());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt("type", MovieType.INTHEATRE.ordinal());
        movieListPresenter = new MovieListPresenterImp(MovieType.values()[mType]);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        movieListPresenter.setView(this);
        if (isVisibleToUser && !isFragmentCreated) {
            movieListPresenter.initialize();
        }
        isFragmentCreated = true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser && !movieListPresenter.isLoaded() && isFragmentCreated) {
            movieListPresenter.initialize();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        registerBus();
        movieListPresenter.resume();
    }

    private void initPresenter() {

    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterBus();
        movieListPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        movieListPresenter.destroy();
    }

    private void initViews() {
        movieListView.setVisibility(View.GONE);

        int spanCount = 3;
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            movieListView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            spanCount = 3;
        } else {
            movieListView.setLayoutManager(new GridLayoutManager(getActivity(), 5));
            spanCount = 5;
        }
        int spacing = 50;
        boolean includeEdge = true;
        movieListView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        movieListView.setItemAnimator(new DefaultItemAnimator());
        movieListAdapter = new MovieListAdapter(new ArrayList<Movie>());
        movieListView.setAdapter(movieListAdapter);
    }

    @Override
    public void showLoading() {
        //loadingView.setVisibility(View.VISIBLE);
        //movieListView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        AnimUtils.crossFade(loadingView, movieListView);
    }

    @Override
    public void renderMovies(List<Movie> movies) {
        Log.d(TAG, "movie: " + movies);
        movieListAdapter.updateItems(movies);
    }

    public void registerBus() {
        MovieBus.getBus().register(this);
    }

    public void unregisterBus() {
        MovieBus.getBus().unregister(this);
    }

    @Subscribe
    public void onMovieClickEvent(MovieClickEvent event) {
        if (isVisibleToUser) {
            movieListPresenter.onMovieClicked(getActivity(), event.getMovie());
        }

    }

}
