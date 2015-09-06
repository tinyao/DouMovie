package im.ycz.doumovie.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import im.ycz.doumovie.R;

/**
 * Created by tinyao on 15-9-1.
 */
public class ZRatingBar extends LinearLayout {

    private final static float DEFAUL_TEXT_SIZE = 12;
    private final static int DEFAULT_STAR_NUM = 5;
    private final static float DEFAULT_STEP_SIZE = 0.5f;
    private final static int DEFAULT_MAX = 10;
    private final static boolean DEFAULT_IS_INDICATOR = false;
    private final static int DEFAULT_TEXT_COLOR = Color.DKGRAY;
    private final static int DEFAULT_SPACING = 4;
    private final static boolean DEFAULT_SHOW_LABEL = true;

    private RatingBar ratingBar;
    private TextView ratingTv;

    private int numStars;
    private float stepSize;
    private int max;
    private boolean isIndicator;
    private float rating;

    private boolean mShowText;
    private int mTextColor;
    private float mTextSize;
    private int mSpacing;

    public ZRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ZRatingBar,
                0, 0);
        try {
            // RatingBar
            numStars = a.getInteger(R.styleable.ZRatingBar_numStars, DEFAULT_STAR_NUM);
            stepSize = a.getFloat(R.styleable.ZRatingBar_stepSize, DEFAULT_STEP_SIZE);
            max = a.getInteger(R.styleable.ZRatingBar_max, DEFAULT_MAX);
            isIndicator = a.getBoolean(R.styleable.ZRatingBar_isIndicator, DEFAULT_IS_INDICATOR);

            // RatingTextView
            mShowText = a.getBoolean(R.styleable.ZRatingBar_showLabel, DEFAULT_SHOW_LABEL);
            mTextColor = a.getColor(R.styleable.ZRatingBar_textColor, DEFAULT_TEXT_COLOR);
            mTextSize = a.getDimensionPixelSize(R.styleable.ZRatingBar_textSize, (int) DEFAUL_TEXT_SIZE);
            mSpacing = a.getDimensionPixelSize(R.styleable.ZRatingBar_spacing,
                    DEFAULT_SPACING * (int) getResources().getDisplayMetrics().density);
        } finally {
            a.recycle();
        }
        init(context, attrs);
    }

    public ZRatingBar(Context context) {
        super(context);
        init(context, null);
    }

    public void init(Context context, AttributeSet attrs) {
//        ratingBar = new RatingBar(context, attrs, R.style.RatingBar);
//        ratingBar.setNumStars(5);
//        ratingTv = new TextView(context);
//        this.setOrientation(HORIZONTAL);
//        this.setGravity(Gravity.CENTER_VERTICAL);
//
//        this.addView(ratingBar, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        layoutParams.setMargins(mSpacing, 0, 0, 0);
        //ratingTv.setLayoutParams(layoutParams);
        //this.addView(ratingTv, layoutParams);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.z_ratingbar, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ratingBar = (RatingBar) findViewById(R.id.zrb_rating);
        ratingTv = (TextView) findViewById(R.id.ztv_rating);
        ratingBar.setNumStars(numStars);
        ratingBar.setStepSize(stepSize);
        ratingBar.setMax(max);
        ratingBar.setRating(rating);
        ratingBar.setIsIndicator(isIndicator);
        ratingTv.setTextSize(mTextSize / getResources().getDisplayMetrics().density);
        ratingTv.setTextColor(mTextColor);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, mSpacing, 0);
        ratingBar.setLayoutParams(layoutParams);
    }

    public void setRating(float rating) {
        this.rating = rating;
        if (rating == 0) {
            ratingBar.setVisibility(GONE);
            ratingTv.setText(R.string.label_no_rating);
        } else {
            ratingBar.setRating(rating / 2);
            ratingBar.setVisibility(VISIBLE);
            ratingTv.setText("" + rating);
        }
    }
}
