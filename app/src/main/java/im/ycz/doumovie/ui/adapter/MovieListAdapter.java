package im.ycz.doumovie.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import im.ycz.doumovie.R;
import im.ycz.doumovie.bus.event.MovieClickEvent;
import im.ycz.doumovie.domain.model.Movie;
import im.ycz.doumovie.bus.MovieBus;
import im.ycz.doumovie.domain.model.Rating;
import im.ycz.doumovie.ui.widget.ZRatingBar;

/**
 * Created by tinyao on 15-8-31.
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieHolder> {

    private List<Movie> movies;

    public MovieListAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_movie, null);
        return new MovieHolder(mView);
    }

    @Override
    public void onBindViewHolder(MovieHolder movieHolder, int position) {
        final MovieHolder mh = (MovieHolder) movieHolder;
        final Movie movie = movies.get(position);
        mh.setMovie(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void updateItems(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public static class MovieHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_title)
        TextView titleV;

        @Bind(R.id.iv_thumbnail)
        ImageView coverV;

        @Bind(R.id.rb_rating)
        ZRatingBar ratingBar;

        private Movie movie;

        public MovieHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 触发事件，跳转到新页面
                    if(movie != null) {
                        MovieBus.getBus().post(new MovieClickEvent(movie));
                    }
                }
            });
        }

        public void setMovie(Movie movie) {
            this.movie = movie;
            titleV.setText(movie.getTitle());
            if (movie.getImage() != null) {
                Picasso.with(coverV.getContext())
                        .load(movie.getImage().getLarge())
                        .into(coverV);
            }
            if (movie.getRating() != null) {
                ratingBar.setRating(movie.getRating().getAverage());
            }
        }
    }

}
