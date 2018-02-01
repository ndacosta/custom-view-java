package com.kickingmobiledev.customviewjava.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IntDef;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.kickingmobiledev.customviewjava.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by nersa on 1/30/18.
 */

public class ResizableTextView extends AppCompatTextView {

    private int aspectRatio;

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

    public ResizableTextView(Context context) {
        this(context, null);
    }

    public ResizableTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ResizableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ResizableTextView);
        aspectRatio = a.getInt(R.styleable.ResizableTextView_aspect_ratio, NORMAL_ASPECT_RATIO);
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

    public void setAspectRatio(@AspectRatio int aspectRatio) {
        if (this.aspectRatio != aspectRatio) {
            this.aspectRatio = aspectRatio;
            requestLayout();
        }
    }
}
