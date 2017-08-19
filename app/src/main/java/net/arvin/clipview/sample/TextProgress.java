package net.arvin.clipview.sample;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Created by arvinljw on 17/8/19 22:21
 * Function：
 * Desc：
 */
public class TextProgress extends ProgressBar {
    private static final String SUPER_DATA = "super_data";
    private static final String TEXT_SIZE = "text_size";
    private static final String TEXT_COLOR = "text_color";
    private static final String TEXT = "text";

    private static final float DEFAULT_TEXT_SIZE = 14f;

    protected String mText;
    protected float textSize;
    protected int textColor;

    protected Paint textPaint;

    public TextProgress(Context context) {
        this(context, null);
    }

    public TextProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TextProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TextProgress(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(attrs);
    }

    private void init(AttributeSet attrs) {
        initAttr(attrs);

        initTextPaint();
    }

    private void initAttr(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TextProgress);
        textSize = typedArray.getDimension(R.styleable.TextProgress_textSize, sp2px(DEFAULT_TEXT_SIZE));
        textColor = typedArray.getColor(R.styleable.TextProgress_textColor, Color.WHITE);
        mText = typedArray.getString(R.styleable.TextProgress_text);
        typedArray.recycle();
    }

    /**
     * 初始化绘制文字的画笔
     */
    private void initTextPaint() {
        textPaint = new Paint();
        textPaint.setColor(textColor);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(textSize);
    }

    public int sp2px(float spValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawText(canvas);
    }

    /**
     * @param canvas 根据状态绘制文字居中显示
     */
    protected void drawText(Canvas canvas) {
        if (TextUtils.isEmpty(mText)) {
            return;
        }
        String value = mText;
        float textWidth = textSize * value.length();
        float x = (getWidth() - textWidth) / 2;
        Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
        float y = (getHeight() - fontMetrics.bottom - fontMetrics.top) / 2;

        float endX = getProgress() * getWidth() / getMax();
        if (endX > x) {
            textPaint.setColor(Color.WHITE);
            canvas.save(Canvas.CLIP_SAVE_FLAG);
            canvas.clipRect(x, 0, endX, getMeasuredHeight());
            canvas.drawText(value, x, y, textPaint);
            canvas.restore();

            if (endX < x + textWidth) {
                textPaint.setColor(textColor);
                canvas.save(Canvas.CLIP_SAVE_FLAG);
                canvas.clipRect(endX, 0, x + textWidth, getMeasuredHeight());
                canvas.drawText(value, x, y, textPaint);
                canvas.restore();
            }
        } else {
            textPaint.setColor(textColor);
            canvas.drawText(value, x, y, textPaint);
        }
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Bundle data = new Bundle();
        Parcelable superState = super.onSaveInstanceState();
        data.putParcelable(SUPER_DATA, superState);
        data.putFloat(TEXT_SIZE, textSize);
        data.putInt(TEXT_COLOR, textColor);
        data.putString(TEXT, mText);
        return data;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        Bundle bundle = (Bundle) state;
        Parcelable superState = bundle.getParcelable(SUPER_DATA);
        super.onRestoreInstanceState(superState);

        textSize = bundle.getFloat(TEXT_SIZE);
        textColor = bundle.getInt(TEXT_COLOR);
        mText = bundle.getString(TEXT);
    }
}
