package com.kickingmobiledev.customviewjava.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.kickingmobiledev.customviewjava.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by nersa on 1/30/18.
 */

public class ResizableTextView extends AppCompatTextView {

    /**
     * This view wants its default size.
     */
    public static final int NORMAL_ASPECT_RATIO = 0;

    /**
     * This view wants an aspect ratio of 1:2.
     */
    public static final int ONE_TO_2_ASPECT_RATIO = 1;

    /**
     * This view wants an aspect ratio of 2:3.
     */
    public static final int TWO_TO_3_ASPECT_RATIO = 2;

    /**
     * This view wants to be square.
     */
    public static final int ONE_TO_1_ASPECT_RATIO = 3;


    @IntDef({NORMAL_ASPECT_RATIO, ONE_TO_2_ASPECT_RATIO, TWO_TO_3_ASPECT_RATIO, ONE_TO_1_ASPECT_RATIO})
    @Retention(RetentionPolicy.SOURCE)
    public @interface AspectRatio {
    }


    private int aspectRatio;

    private Drawable frontDrawable;
    private int frontDrawableResource;

    private boolean mIsDragging;

    public ResizableTextView(Context context) {
        this(context, null);
    }

    public ResizableTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ResizableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ResizableTextView);
        final int N = a.getIndexCount();
        for (int i = 0; i < N; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.ResizableTextView_front_drawable:
                    frontDrawable = a.getDrawable(attr);
                    if (frontDrawable != null) {
                        frontDrawable.setBounds(0, 0,
                                frontDrawable.getIntrinsicWidth(),
                                frontDrawable.getIntrinsicHeight());
                    }
                    break;
                case R.styleable.ResizableTextView_aspect_ratio:
                    aspectRatio = a.getInt(R.styleable.ResizableTextView_aspect_ratio, NORMAL_ASPECT_RATIO);
                    break;
            }
        }
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (aspectRatio == NORMAL_ASPECT_RATIO) {
            return;
        }
        int size = Math.max(getMeasuredWidth(), getMeasuredHeight());
        int widthSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
        int calculatedSize = calculateSize(size);
        int heightSpec = MeasureSpec.makeMeasureSpec(calculatedSize, MeasureSpec.EXACTLY);
        super.onMeasure(widthSpec, heightSpec);
    }

    private int calculateSize(int originalSize) {
        switch (aspectRatio) {
            case ONE_TO_1_ASPECT_RATIO:
                return originalSize;
            case ONE_TO_2_ASPECT_RATIO:
                return originalSize / 2;
            case TWO_TO_3_ASPECT_RATIO:
                return (originalSize * 2) / 3;
            default:
                return -1;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (frontDrawable != null) {
            frontDrawable.draw(canvas);
        }
    }

    @Override
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();

        if (frontDrawable != null) {
            frontDrawable.jumpToCurrentState();
        }
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();

        final int[] state = getDrawableState();
        boolean changed = false;

        final Drawable fd = frontDrawable;
        if (fd != null && fd.isStateful()) {
            changed = fd.setState(state);
        }
        if (changed) {
            invalidate();
        }
    }

    /**
     * Handles drawable selection and movement
     */
    @Override
    public synchronized boolean onTouchEvent(MotionEvent event) {

        if (!isEnabled() || frontDrawable == null)
            return false;

        final int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                final int mActivePointerId = event.getPointerId(event.getPointerCount() - 1);
                final float x = event.getX(mActivePointerId);
                final float y = event.getY(mActivePointerId);
                Rect rect = frontDrawable.getBounds();
                setPressed(true);
                if (rect.contains((int) x, (int) y)) {
                    onStartTrackingTouch();
                    trackTouchEvent(event);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mIsDragging) {
                    trackTouchEvent(event);
                }
                break;
            case MotionEvent.ACTION_UP:
                performClick();
            case MotionEvent.ACTION_CANCEL:
                setPressed(false);
                if (mIsDragging) {
                    onStopTrackingTouch();
                }
                break;
        }
        return true;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    private void onStartTrackingTouch() {
        mIsDragging = true;
    }

    private void onStopTrackingTouch() {
        mIsDragging = false;
    }

    protected void trackTouchEvent(MotionEvent event) {
        final int pointerIndex = event.findPointerIndex(event.getPointerCount() - 1);
        final int x = (int) event.getX(pointerIndex);
        final int y = (int) event.getY(pointerIndex);
        frontDrawable.setBounds(x - frontDrawable.getIntrinsicWidth() / 2,
                y - frontDrawable.getIntrinsicHeight() / 2,
                frontDrawable.getIntrinsicWidth() / 2 + x,
                frontDrawable.getIntrinsicHeight() / 2 + y);
        invalidate();
    }

    public void setAspectRatio(@AspectRatio int aspectRatio) {
        if (this.aspectRatio != aspectRatio) {
            this.aspectRatio = aspectRatio;
            requestLayout();
        }
    }

    public int getAspectRatio() {
        return aspectRatio;
    }

    public void setFrontDrawable(@Nullable Drawable frontDrawable) {
        if (this.frontDrawable != frontDrawable) {
            this.frontDrawable = frontDrawable;
            if (frontDrawable != null) {
                frontDrawable.setBounds(0, 0,
                        frontDrawable.getIntrinsicWidth(),
                        frontDrawable.getIntrinsicHeight());
            }
            invalidate();
            frontDrawableResource = 0;
        }
    }

    public void setFrontDrawableResource(@DrawableRes int resId) {
        if (resId != 0 && resId == frontDrawableResource) {
            return;
        }

        Drawable d = null;
        if (resId != 0) {
            d = ContextCompat.getDrawable(getContext(), resId);
        }
        setFrontDrawable(d);

        frontDrawableResource = resId;
    }

    public Drawable getFrontDrawable() {
        return frontDrawable;
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        SavedState ss = new SavedState(superState);
        ss.aspectRatio = aspectRatio;

        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        setAspectRatio(ss.aspectRatio);
    }

    public static class SavedState extends BaseSavedState {
        int aspectRatio = NORMAL_ASPECT_RATIO;

        SavedState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(aspectRatio);
        }

        @Override
        public String toString() {
            String str = "ResizableTextView.SavedState{"
                    + Integer.toHexString(System.identityHashCode(this))
                    + " aspectRatio=" + aspectRatio;
            return str + "}";
        }

        @SuppressWarnings("hiding")
        public static final Parcelable.Creator<ResizableTextView.SavedState> CREATOR =
                new Parcelable.Creator<ResizableTextView.SavedState>() {
                    public ResizableTextView.SavedState createFromParcel(Parcel in) {
                        return new ResizableTextView.SavedState(in);
                    }

                    public ResizableTextView.SavedState[] newArray(int size) {
                        return new ResizableTextView.SavedState[size];
                    }
                };

        private SavedState(Parcel in) {
            super(in);
            aspectRatio = in.readInt();
        }
    }
}
