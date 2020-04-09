package com.blue.cameraxdemo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * Created by Android Studio.
 *
 * @author zjx
 * @date 2020/3/18
 */
@SuppressLint("AppCompatCustomView")
public class MyTextView extends TextView {
    private int mInnerColor;
    private int mOuterColor;

    private TextPaint mPaint;

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }



    public void setmMyColor(int mInnerColor, int mOuterColor) {
        this.mInnerColor = mInnerColor;
        this.mOuterColor = mOuterColor;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获取宽的测量模式
        int wSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        // 获取符控件提供的 view 宽的最大值
        int wSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int hSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (wSpecMode == MeasureSpec.AT_MOST && hSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(300, 300);
        } else if (wSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(300, hSpecSize);
        } else if (hSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(wSpecSize, 300);
        }
    }

    private String mText;


    @Override
    protected void onDraw(Canvas canvas) {
        mText=getText().toString();
        mPaint=getPaint();
        mPaint.setTextSize(60);
        mPaint.setColor(Color.argb(Color.alpha(mOuterColor), Color.red(mOuterColor), Color.green(mOuterColor), Color.blue(mOuterColor)));
        mPaint.setStrokeWidth(7);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setFakeBoldText(true);
        StaticLayout staticLayout =StaticLayout.Builder.obtain(mText, 0, mText.length(), mPaint, 1200).build();
        staticLayout.draw(canvas);
        /**
         * 内
         */
        mPaint.setColor(Color.argb(Color.alpha(mInnerColor), Color.red(mInnerColor), Color.green(mInnerColor), Color.blue(mInnerColor)));
        mPaint.setStrokeWidth(0);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setFakeBoldText(false);
        StaticLayout staticLayout2 =StaticLayout.Builder.obtain(mText, 0, mText.length(), mPaint, 1200).build();
        staticLayout2.draw(canvas);
    }


}
