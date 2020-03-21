package com.blue.cameraxdemo.spannable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.text.TextPaint;
import android.text.style.ReplacementSpan;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.blue.cameraxdemo.R;

/**
 * Created by Android Studio.
 *
 * @author zjx
 * @date 2020/1/10
 */
public class StrokeSpan extends ReplacementSpan {
    private int mInnerColor;
    private int mOuterColor;
    /**
     * 换行宽度
     */
    private int mWidth;
    private int mHeight;

    public StrokeSpan(int mInnerColor, int mOuterColor, int width) {
        this.mInnerColor = mInnerColor;
        this.mOuterColor = mOuterColor;
        this.mWidth = width;
    }

    /**
     * @param paint
     * @param text
     * @param start
     * @param end
     * @param fm
     * @return span的宽度
     */
    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
        int width = (int) paint.measureText(text, 0, text.length() - 1);
        Log.v("TAG", "-----getSize--- width=" + width + "  start=" + start + "  end=" + end + "  fm=" + fm);
//        return width;
        return (int) paint.measureText(text,start,end);
    }

    /**
     * @param canvas
     * @param text
     * @param start
     * @param end
     * @param x      原点坐标 x
     * @param top    该行顶部 到 最顶部的距离
     * @param y      原点坐标 y
     * @param bottom 当前行底部 到  最顶部的距离    bottom-top=行高
     * @param paint
     */
    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        Log.v("TAG", "-------- text=" + text + " start=" + start + "  end=" + end + "  x=" + x + "  top=" + top + "  y=" + y + "  bottom=" + bottom + "  ");
        Log.v("TAG", "-------- canvas_width=" + canvas.getWidth() + "  canvas_height=" + canvas.getHeight());

        paint.setColor(Color.argb(Color.alpha(mOuterColor), Color.red(mOuterColor), Color.green(mOuterColor), Color.blue(mOuterColor)));
        canvas.drawColor(mInnerColor);
        Log.v("TAG", "------- 原点坐标 x = " + x + " y = " + y);
        canvas.drawText(text, start, end, x, y, paint);

//        // 外
//        paint.setColor(Color.argb(Color.alpha(mOuterColor), Color.red(mOuterColor), Color.green(mOuterColor), Color.blue(mOuterColor)));
//        paint.setStrokeWidth(0);
//        paint.setStyle(Paint.Style.FILL_AND_STROKE);
//        paint.setFakeBoldText(true);
//        canvas.drawText(text, 0, text.length(), 0, canvas.getHeight(), paint);
//        /**
//         * 内
//         */
//        paint.setColor(Color.argb(Color.alpha(mInnerColor), Color.red(mInnerColor), Color.green(mInnerColor), Color.blue(mInnerColor)));
//        paint.setStrokeWidth(0);
//        paint.setStyle(Paint.Style.FILL);
//        paint.setFakeBoldText(false);
//        canvas.drawText(text, 0, text.length(), x, y, paint);

        /**
         * 描外层
         */
        paint.setColor(Color.argb(Color.alpha(mOuterColor), Color.red(mOuterColor), Color.green(mOuterColor), Color.blue(mOuterColor)));
        // 描边宽度
        paint.setStrokeWidth(2);
        // 描边种类
        paint.setStyle(Paint.Style.STROKE);
        // 外层text采用粗体
        paint.setFakeBoldText(true);
        canvas.drawText(text, start, end, x, y, paint);
        /**
         * 描内层，恢复原先的画笔
         */
        paint.setColor(Color.argb(Color.alpha(mInnerColor), Color.red(mInnerColor), Color.green(mInnerColor), Color.blue(mInnerColor)));
        paint.setStyle(Paint.Style.FILL);
        paint.setFakeBoldText(false);
        canvas.drawText(text, start, end, x, y, paint);

    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
    }
}
