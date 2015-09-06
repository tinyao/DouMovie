package im.ycz.doumovie.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import im.ycz.doumovie.R;

/**
 * Created by tinyao on 15-9-1.
 */
public class ZRatingBar2 extends LinearLayout {

    private enum POSITION {
        LEFT(0), RIGHT(1), TOP(2), BOTTOM(3);
        private int value;
        private POSITION(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    };

    private boolean mShowText;
    private POSITION mTextPos;
    private int mTextColor;
    private int mTextSize;

    public ZRatingBar2(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ZRatingBar,
                0, 0);

        try {
            mShowText = a.getBoolean(R.styleable.ZRatingBar_showLabel, false);
            mTextColor = a.getColor(R.styleable.ZRatingBar_textColor, Color.DKGRAY);
            mTextSize = a.getDimensionPixelSize(R.styleable.ZRatingBar_textSize, 11);
        } finally {
            a.recycle();
        }

        init();
    }

    public ZRatingBar2(Context context) {
        super(context);
        init();
    }

    private Paint mTextPaint;

    public void init() {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextColor);
    }

    int textWidth;

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        textWidth = (int) (mTextSize * getResources().getDisplayMetrics().density);

        Log.d("DEBUG", "textWidth: " + textWidth);

        int minw = getMeasuredWidth() + textWidth ;

        setMeasuredDimension(minw, getMeasuredHeight());
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {

        Canvas originalCanvas = canvas;

        canvas.clipRect(0, 0, getMeasuredWidth() - textWidth, getMeasuredHeight());
        super.onDraw(canvas);

        int width = this.getMeasuredWidth() / 2;
        int height = this.getMeasuredHeight() / 2;
        Paint mPaint = new Paint();
        mPaint.setTextSize(30);
        mPaint.setColor(Color.BLACK);
        canvas.drawText("Hello", width, height, mPaint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

}
