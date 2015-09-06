package im.ycz.doumovie.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by tinyao on 15-9-1.
 */
public class LoadingView extends ProgressBar {

    public LoadingView(Context context) {
        super(context);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void show() {
        this.setVisibility(View.VISIBLE);
    }

    public void hide() {

    }
}
