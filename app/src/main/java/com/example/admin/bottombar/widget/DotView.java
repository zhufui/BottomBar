package com.example.admin.bottombar.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;

import com.example.admin.bottombar.R;

/**
 * 小红点,未读消息，未读数量
 */
public class DotView extends AppCompatTextView {
    public static final String TAG = DotView.class.getSimpleName();
    private Paint mPaint;
    private int mDotPadding;

    private static final String DEFAULT_OVER_TEXT = "99+";
    private static final String DEFAULT_NO_NUMBER = "NO";//不是数字
    private static final int DEFAULT_PADDING = 3;
    private static final String DEFAULT_TEXT = "88";

    public DotView(Context context) {
        super(context);
    }

    public DotView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DotView, 0, 0);
        int color = typedArray.getColor(R.styleable.DotView_dotColor, Color.RED);
        float density = getResources().getDisplayMetrics().density;
        mDotPadding = typedArray.getDimensionPixelSize(R.styleable.DotView_dotPadding, (int) (DEFAULT_PADDING * density));
        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setColor(color);
        mPaint.setAntiAlias(true);

        setPadding(0, 0, 0, 0);
        setGravity(Gravity.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (validText()) {
            RectF rectF = new RectF(0, 0, getWidth(), getHeight());
            canvas.drawRoundRect(rectF, getWidth() / 2, getWidth() / 2, mPaint);
        } else {
            final int radius = getWidth() / 2;
            canvas.drawCircle(radius, radius, radius, mPaint);
        }
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int newWidthMeasureSpec;
        int newHeightMeasureSpec;
        if (validText()) {
            int width = getTextWidth() + mDotPadding * 2;
            int height = getTextHeight() + mDotPadding * 2;
            newWidthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
            newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        } else {
            int width = 0;
            if (getText().length() > 0) {
                width = Math.max(getTextWidth(), getTextHeight());
            }
            width += mDotPadding * 2;
            int newMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
            newWidthMeasureSpec = newMeasureSpec;
            newHeightMeasureSpec = newMeasureSpec;
        }
        super.onMeasure(newWidthMeasureSpec, newHeightMeasureSpec);
    }

    /**
     * 验证文本是否是99+
     *
     * @return
     */
    private boolean validText() {
        String text = getText().toString();
        if (DEFAULT_OVER_TEXT.equals(text)) {
            return true;
        }
        return false;
    }

    private int getTextWidth() {
        return (int) getPaint().measureText(getText().toString());
    }

    private int getTextHeight() {
        final Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
        return (int) (fontMetrics.descent - fontMetrics.ascent);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (text.length() > 0) {
            final String testStr = text.toString();
            if (!isNumber(testStr)) {
                text = DEFAULT_NO_NUMBER;
            } else if (!isLegalNumber(Integer.valueOf(testStr))) {
                text = DEFAULT_OVER_TEXT;
            }
        }
        super.setText(text, type);
    }

    private boolean isNumber(String text) {
        if (TextUtils.isEmpty(text)) {
            return false;
        }
        return text.matches("\\d+");
    }

    private boolean isLegalNumber(int number) {
        return number >= 0 && number < 100;
    }
}
