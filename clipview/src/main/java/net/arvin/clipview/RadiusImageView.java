package net.arvin.clipview;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Parcelable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by arvinljw on 17/8/19 21:33
 * Function：
 * Desc：
 */
public class RadiusImageView extends AppCompatImageView {
    private RadiusUtils mRadiusUtils;

    public RadiusImageView(Context context) {
        super(context);
        mRadiusUtils = new RadiusUtils(context, null);
    }

    public RadiusImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRadiusUtils = new RadiusUtils(context, attrs);
    }

    public RadiusImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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

    public RadiusImageView setRadius(float radius) {
        mRadiusUtils.setRadius(radius);
        return this;
    }

    public RadiusImageView setRadiusLeftTop(int radiusLeftTop) {
        mRadiusUtils.setRadiusLeftTop(radiusLeftTop);
        return this;
    }

    public RadiusImageView setRadiusLeftBottom(float radiusLeftBottom) {
        mRadiusUtils.setRadiusLeftBottom(radiusLeftBottom);
        return this;
    }

    public RadiusImageView setRadiusRightTop(float radiusRightTop) {
        mRadiusUtils.setRadiusRightTop(radiusRightTop);
        return this;
    }

    public RadiusImageView setRadiusRightBottom(float radiusRightBottom) {
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
