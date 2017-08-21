package net.arvin.clipview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by arvinljw on 17/8/18 11:07
 * Function：
 * Desc：
 */
public class RadiusRelativeLayout extends RelativeLayout {
    private RadiusUtils mRadiusUtils;

    public RadiusRelativeLayout(Context context) {
        this(context, null);
    }

    public RadiusRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadiusRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RadiusRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mRadiusUtils = new RadiusUtils(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRadiusUtils.onSizeChanged(w, h);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.clipPath(mRadiusUtils.getPath());
        super.draw(canvas);
        canvas.restore();
    }

    public RadiusRelativeLayout setRadius(float radius) {
        mRadiusUtils.setRadius(radius);
        return this;
    }

    public RadiusRelativeLayout setRadiusLeftTop(int radiusLeftTop) {
        mRadiusUtils.setRadiusLeftTop(radiusLeftTop);
        return this;
    }

    public RadiusRelativeLayout setRadiusLeftBottom(float radiusLeftBottom) {
        mRadiusUtils.setRadiusLeftBottom(radiusLeftBottom);
        return this;
    }

    public RadiusRelativeLayout setRadiusRightTop(float radiusRightTop) {
        mRadiusUtils.setRadiusRightTop(radiusRightTop);
        return this;
    }

    public RadiusRelativeLayout setRadiusRightBottom(float radiusRightBottom) {
        mRadiusUtils.setRadiusRightBottom(radiusRightBottom);
        return this;
    }

    public float getRadius() {
        return mRadiusUtils.getRadius();
    }

    public float getRadiusLeftTop() {
        return mRadiusUtils.getRadiusLeftTop();
    }

    public float getRadiusLeftBottom() {
        return mRadiusUtils.getRadiusLeftBottom();
    }

    public float getRadiusRightTop() {
        return mRadiusUtils.getRadiusRightTop();
    }

    public float getRadiusRightBottom() {
        return mRadiusUtils.getRadiusRightBottom();
    }


    @Override
    public Parcelable onSaveInstanceState() {
        return mRadiusUtils.onSaveInstanceState(super.onSaveInstanceState());
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(mRadiusUtils.onRestoreInstanceState(state));
        mRadiusUtils.onSizeChanged(getWidth(), getHeight());
    }
}
