package com.blue.cameraxdemo.spannable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.os.Parcel;
import android.text.DynamicLayout;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannedString;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ReplacementSpan;
import android.text.style.TextAppearanceSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.textclassifier.TextLinks;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.IntStream;


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
    private Canvas mCanvas;

    public StrokeSpan(int mInnerColor, int mOuterColor) {
        this.mInnerColor = mInnerColor;
        this.mOuterColor = mOuterColor;
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
        Log.v("TAG","-----getSize--- text="+text+"  start="+start+"  end="+end);
        return (int) paint.measureText(text, start, end);
    }

    /**
     * @param canvas
     * @param text
     * @param start
     * @param end
     * @param x      原点坐标 x
     * @param top
     * @param y      原点坐标 y
     * @param bottom
     * @param paint
     */
    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        Log.v("TAG", "-------- text=" +text + " start=" + start + "  end=" + end + "  x=" + x+"  top="+top+"  y="+y+"  bottom="+bottom+"  ");
        Log.v("TAG","-------- canvas_width="+canvas.getWidth()+"  canvas_height="+canvas.getHeight());

        // 外
        paint.setColor(Color.argb(Color.alpha(mOuterColor), Color.red(mOuterColor), Color.green(mOuterColor), Color.blue(mOuterColor)));
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setFakeBoldText(true);
        paint.measureText(text, start, end);
        canvas.drawText(text, start, end, x, y, paint);
        /**
         * 内
         */
        paint.setColor(Color.argb(Color.alpha(mInnerColor), Color.red(mInnerColor), Color.green(mInnerColor), Color.blue(mInnerColor)));
        paint.setStrokeWidth(0);
        paint.setStyle(Paint.Style.FILL);
        paint.setFakeBoldText(false);
        canvas.drawText(text, start, end, x, y, paint);



//        /**
//         * 描外层
//         */
//        paint.setColor(Color.argb(Color.alpha(mOuterColor),Color.red(mOuterColor),Color.green(mOuterColor),Color.blue(mOuterColor)));
//        // 描边宽度
//        paint.setStrokeWidth(2);
//        // 描边种类
//        paint.setStyle(Paint.Style.FILL_AND_STROKE);
//        // 外层text采用粗体
//        paint.setFakeBoldText(true);
//        canvas.drawText(text, start, end, x , y, paint);
//        /**
//         * 描内层，恢复原先的画笔
//         */
//        paint.setColor(Color.argb(Color.alpha(mInnerColor),Color.red(mInnerColor),Color.green(mInnerColor),Color.blue(mInnerColor)));
//        paint.setStyle(Paint.Style.FILL);
//        paint.setFakeBoldText(false);
//        canvas.drawText(text, start, end, x , y, paint);


    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
    }
}
