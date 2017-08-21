package net.arvin.clipview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;

/**
 * Created by arvinljw on 17/8/18 11:10
 * Function：
 * Desc：
 */
public class RadiusUtils {
    private static final String SUPER_DATA = "super_data";
    private static final String DATA_RADII = "data_radii";

    private Path mPath;
    private RectF mRectF;
    private float[] radii;

    private float radius;
    private float radiusLeftTop;
    private float radiusLeftBottom;
    private float radiusRightTop;
    private float radiusRightBottom;

    public RadiusUtils(Context context, AttributeSet attrs) {
        initAttr(context, attrs);

        radii = new float[8];

        resetRadius();

        mPath = new Path();
    }

    private void resetRadius() {
        radii[0] = radii[1] = radiusLeftTop;
        radii[2] = radii[3] = radiusRightTop;
        radii[4] = radii[5] = radiusRightBottom;
        radii[6] = radii[7] = radiusLeftBottom;
    }

    private void initAttr(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RadiusView);
        radiusLeftTop = array.getDimension(R.styleable.RadiusView_radius_left_top, 0);
        radiusLeftBottom = array.getDimension(R.styleable.RadiusView_radius_left_bottom, 0);
        radiusRightTop = array.getDimension(R.styleable.RadiusView_radius_right_top, 0);
        radiusRightBottom = array.getDimension(R.styleable.RadiusView_radius_right_bottom, 0);
        radius = array.getDimension(R.styleable.RadiusView_radius, 0);

        array.recycle();

        if (radius != 0) {
            radiusLeftTop = radius;
            radiusLeftBottom = radius;
            radiusRightTop = radius;
            radiusRightBottom = radius;
        }
    }

    public void onSizeChanged(int w, int h) {
        mRectF = new RectF(0, 0, w, h);
    }

    public Path getPath() {
        mPath.reset();
        mPath.addRoundRect(mRectF, radii, Path.Direction.CW);
        return mPath;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        radiusLeftTop = radius;
        radiusLeftBottom = radius;
        radiusRightTop = radius;
        radiusRightBottom = radius;
        resetRadius();
    }

    public void setRadiusLeftTop(float radiusLeftTop) {
        this.radiusLeftTop = radiusLeftTop;
        resetRadius();
    }

    public void setRadiusLeftBottom(float radiusLeftBottom) {
        this.radiusLeftBottom = radiusLeftBottom;
        resetRadius();
    }

    public void setRadiusRightTop(float radiusRightTop) {
        this.radiusRightTop = radiusRightTop;
        resetRadius();
    }

    public void setRadiusRightBottom(float radiusRightBottom) {
        this.radiusRightBottom = radiusRightBottom;
        resetRadius();
    }

    public float getRadius() {
        return radius;
    }

    public float getRadiusLeftTop() {
        return radiusLeftTop;
    }

    public float getRadiusLeftBottom() {
        return radiusLeftBottom;
    }

    public float getRadiusRightTop() {
        return radiusRightTop;
    }

    public float getRadiusRightBottom() {
        return radiusRightBottom;
    }

    public Parcelable onSaveInstanceState(Parcelable superState) {
        Bundle data = new Bundle();
        data.putParcelable(SUPER_DATA, superState);
        data.putFloatArray(DATA_RADII, radii);
        return data;
    }

    public Parcelable onRestoreInstanceState(Parcelable state) {
        Bundle bundle = (Bundle) state;
        radii = bundle.getFloatArray(DATA_RADII);
        if (radii != null) {
            radiusLeftTop = radii[0];
            radiusRightTop = radii[2];
            radiusRightBottom = radii[4];
            radiusLeftBottom = radii[6];
        }

        return bundle.getParcelable(SUPER_DATA);
    }
}
