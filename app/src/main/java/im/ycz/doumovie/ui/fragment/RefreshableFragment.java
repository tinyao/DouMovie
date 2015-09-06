package im.ycz.doumovie.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import butterknife.Bind;
import im.ycz.doumovie.R;

/**
 * Created by tinyao on 15-8-31.
 */
public abstract class RefreshableFragment extends BaseFragment{

    public SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupSwipeRefreshLayout(view);
    }

    public void setupSwipeRefreshLayout(View view) {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                RefreshableFragment.this.onRefresh();
            }
        });
    }

    public abstract void onRefresh();

    private boolean refreshing;

    public boolean isRefreshing() {
        return mSwipeRefreshLayout.isRefreshing();
    }

    public void setRefreshing(boolean refreshing) {
        if (mSwipeRefreshLayout == null) return;
        mSwipeRefreshLayout.setRefreshing(refreshing);
//        if (!refreshing) {
//            mSwipeRefreshLayout.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    mSwipeRefreshLayout.setRefreshing(false);
//                }
//            }, 500);
//        } else {
//            mSwipeRefreshLayout.setRefreshing(true);
//        }
    }
}
