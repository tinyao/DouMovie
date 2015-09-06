package im.ycz.doumovie.ui.fragment;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import im.ycz.doumovie.R;
import im.ycz.doumovie.domain.model.Cast;
import im.ycz.doumovie.domain.model.Director;
import im.ycz.doumovie.domain.model.Movie;
import im.ycz.doumovie.ui.adapter.CastAdapter;
import im.ycz.doumovie.ui.presenter.MovieDetailPresenter;
import im.ycz.doumovie.ui.presenter.MovieDetailPresenterImp;
import im.ycz.doumovie.ui.view.MovieDetailView;
import im.ycz.doumovie.ui.widget.ZRatingBar;
import im.ycz.doumovie.utils.anim.AnimUtils;
import im.ycz.doumovie.utils.view.GridSpacingItemDecoration;
import im.ycz.doumovie.utils.view.HorizontalSpacingItemDecoration;
import im.ycz.doumovie.utils.view.PaletteTransformation;

/**
 * Created by tinyao on 15-8-31.
 */
public class MovieDetailFragment extends BaseFragment implements MovieDetailView, ObservableScrollViewCallbacks{

    private MovieDetailPresenter movieDetailPresenter;

    public enum LIST_TYPE {
        INTHEATRE, COMING, NEWSHOW, WEEKLY, US, TOP250
    };

    @Bind(R.id.scroll)
    ObservableScrollView mScrollView;

    @Bind(R.id.iv_detail_header)
    ImageView headerImgV;
    @Bind(R.id.iv_detail_cover)
    ImageView coverV;
    @Bind(R.id.tv_detail_title)
    TextView titleV;
    @Bind(R.id.tv_detail_categary)
    TextView catV;
    @Bind(R.id.tv_detail_country)
    TextView countryV;
    @Bind(R.id.rb_detail_rating)
    ZRatingBar ratingBar;
    @Bind(R.id.tv_detail_rating_count)
    TextView ratingCountV;

    @Bind(R.id.list_detail_casts)
    RecyclerView castsList;

    @Bind(R.id.layout_detail_main)
    View detailMain;

    @Bind(R.id.tv_detail_summary)
    TextView summaryV;

    @Bind(R.id.loading)
    ProgressBar loadingV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieDetailPresenter = new MovieDetailPresenterImp();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
//        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        activity.setSupportActionBar(toolbar);
//        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initPresenter();
        final Movie movie = getMovie();
        renderMovie(movie);

        // 获取全部信息，包含 summary
        movieDetailPresenter.onMovieReceived(movie);
    }

    private void initView() {
        mScrollView.setScrollViewCallbacks(this);
        castsList.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        castsList.addItemDecoration(new HorizontalSpacingItemDecoration(
                getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin)));
        summaryV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (summaryV.getMaxLines() != 100) {
                    summaryV.setMaxLines(100);
                } else {
                    summaryV.setMaxLines(10);
                }
            }
        });
    }

    private void initPresenter() {
        movieDetailPresenter.setView(this);
        movieDetailPresenter.initialize();
    }

    public Movie getMovie() {
        return (Movie) getActivity().getIntent().getSerializableExtra("movie");
    }

    private ProgressDialog pd;

    @Override
    public void showLoading() {
        loadingV.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        AnimUtils.crossFade(loadingV, detailMain);
    }

    @Override
    public void renderMovie(final Movie movie) {
        if (movie.getPhotos() != null && movie.getPhotos().size() > 0) {
            Picasso.with(getActivity())
                    .load(movie.getPhotos().get(0).getImage())
                    .into(headerImgV);
        }
        Picasso.with(getActivity())
                .load(movie.getImage().getLarge())
                .transform(PaletteTransformation.instance())
                .into(coverV, new Callback.EmptyCallback(){
                    @Override
                    public void onSuccess() {
                        Bitmap bitmap = ((BitmapDrawable) coverV.getDrawable()).getBitmap(); // Ew!
                        Palette palette = PaletteTransformation.getPalette(bitmap);
                        Palette.Swatch swatch = palette.getVibrantSwatch();
                        headerImgV.setBackgroundColor(swatch.getRgb());
                    }
                });
        titleV.setText(movie.getTitle());
        ratingBar.setRating(movie.getRating().getAverage());
        ratingCountV.setText("(" + movie.getRatingCount() + "人评分" + ")");
        catV.setText(movie.getGenresFormated());
        countryV.setText(movie.getCountriesFormated() + " / " + movie.getYear());

        summaryV.setText(movie.getSummary());

        if (movie.getCasts() != null) {
            List<Cast> showCasts = new ArrayList<>();
            showCasts.addAll(movie.getDirectors());
            showCasts.addAll(movie.getCasts());
            castsList.setAdapter(new CastAdapter(showCasts));
        }
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = getResources().getColor(R.color.primary);
        headerImgV.animate().translationY(-scrollY/2).setDuration(0).start();
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }
}
