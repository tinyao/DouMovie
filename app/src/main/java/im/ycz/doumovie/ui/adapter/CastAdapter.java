package im.ycz.doumovie.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import im.ycz.doumovie.R;
import im.ycz.doumovie.domain.model.Cast;
import im.ycz.doumovie.domain.model.Movie;

/**
 * Created by tinyao on 15-9-4.
 */
public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastHolder> {

    private List<Cast> casts;

    public CastAdapter(List<Cast> casts) {
        this.casts = casts;
    }


    @Override
    public CastHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_cast, null);
        return new CastHolder(mView);
    }

    @Override
    public void onBindViewHolder(CastHolder movieHolder, int position) {
        final CastHolder mh = (CastHolder) movieHolder;
        final Cast cast = casts.get(position);
        mh.setCast(cast);
    }

    @Override
    public int getItemCount() {
        return casts.size();
    }

    public void updateItems(List<Cast> casts) {
        this.casts = casts;
        notifyDataSetChanged();
    }

    public class CastHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.tv_cast_name)
        TextView nameV;

        @Bind(R.id.tv_cast_title)
        TextView titleV;

        @Bind(R.id.iv_cast_avatar)
        ImageView avatarV;

        public CastHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setCast(Cast cast) {
            Picasso.with(avatarV.getContext())
                    .load(cast.getAvatar().getLarge())
                    .into(avatarV);
            nameV.setText(cast.getName());
            if (cast.isDirector()) {
                titleV.setText("导演");
            } else {
                titleV.setText("");
            }
        }

    }
}
