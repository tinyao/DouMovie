package im.ycz.doumovie.utils.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/**
 * Created by tinyao on 15-9-1.
 */
public class AnimUtils {


    public static void crossFade(final View source, final View target) {

        int mShortAnimationDuration = source.getResources().getInteger(android.R.integer.config_shortAnimTime);

        target.setAlpha(0f);
        target.setVisibility(View.VISIBLE);

        target.animate()
                .alpha(1f)
                .setDuration(mShortAnimationDuration)
                .setListener(null);

        source.animate()
                .alpha(0f)
                .setDuration(mShortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        source.setVisibility(View.GONE);
                    }
                });

    }

}
