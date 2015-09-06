package im.ycz.doumovie.utils.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by tinyao on 15-8-31.
 */
public class HorizontalSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spacing;

    public HorizontalSpacingItemDecoration(int spacing) {
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item_list_cast position
        outRect.left = spacing;
        outRect.left = spacing;
        outRect.right = 0;
        if (position == (parent.getAdapter().getItemCount() - 1)) {
            outRect.right = spacing;
        }
    }
}