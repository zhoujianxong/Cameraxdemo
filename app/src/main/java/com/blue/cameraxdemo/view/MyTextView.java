package com.blue.cameraxdemo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
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
    protected void onDraw(Canvas canvas) {
        TextPaint paint = new TextPaint();
        paint.setTextSize(60);
        paint.setColor(Color.argb(Color.alpha(mOuterColor), Color.red(mOuterColor), Color.green(mOuterColor), Color.blue(mOuterColor)));
        paint.setStrokeWidth(0);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setFakeBoldText(true);
        canvas.drawText(getText(), 0, getText().length(), 0, 20, paint);
        /**
         * 内
         */
        paint.setColor(Color.argb(Color.alpha(mInnerColor), Color.red(mInnerColor), Color.green(mInnerColor), Color.blue(mInnerColor)));
        paint.setStrokeWidth(0);
        paint.setStyle(Paint.Style.FILL);
        paint.setFakeBoldText(false);
        canvas.drawText(getText(), 0, getText().length(), 0, 20, paint);
        super.onDraw(canvas);
    }


}
