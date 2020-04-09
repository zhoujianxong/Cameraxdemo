package com.blue.cameraxdemo.spannable;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import android.text.style.ReplacementSpan;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blue.cameraxdemo.R;

/**
 * Created by Android Studio.
 * @author zjx
 * @date 2020/1/10
 */
public class StrokeSpan extends ReplacementSpan {
    private int mInnerColor;
    private int mOuterColor;

    private float mTextSize;

    public StrokeSpan(int mInnerColor, int mOuterColor,float textSize) {
        this.mInnerColor = mInnerColor;
        this.mOuterColor = mOuterColor;
        this.mTextSize=textSize;
    }

    /**
     * @param paint
     * @param text
     * @param start
     * @param end
     * @param fm
     * @return 要绘制的宽度
     */
    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
//        int width = (int) paint.measureText(text, 0, text.length() - 1);
//        Log.v("TAG", "-----getSize--- width=" + width + "  start=" + start + "  end=" + end + "  fm=" + fm);
//        return width;
//        return (int) paint.measureText(text,start,end);

        float textWidth=paint.measureText(text,start,end);
        Log.v("TAG", "-----textWidth = " + textWidth);
        return (int) textWidth;
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
     * @param
     */
    @SuppressLint("ResourceAsColor")
    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        //一行多少字
        int singleRowNumber=(int) (canvas.getWidth()/mTextSize);
        //行数
        int row=(int) Math.ceil((double) (text.length()/singleRowNumber));
//        Log.v("TAG", "-------- canvas=" + canvas.getWidth() + "  canvas_height=" + canvas.getHeight());
//        paint.setColor(Color.argb(Color.alpha(mOuterColor), Color.red(mOuterColor), Color.green(mOuterColor), Color.blue(mOuterColor)));
//        canvas.drawColor(mInnerColor);
//        canvas.drawText(text, start, end, x, y, paint);
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
        Log.v("TAG", "-----canvas  w =" + canvas.getWidth()+" h ="+canvas.getHeight());
        Log.v("TAG", "-----canvas  start =" + start+" end ="+end);
        Log.v("TAG", "-----canvas  x =" + x+" y ="+y);
        Log.v("TAG", "-----canvas  mTextSize =" +mTextSize);
        Log.v("TAG", "-----canvas  row =" +row);
        Log.v("TAG", "-----------------------------------------------------------------------------------------------------");


        /**
         * 描外层
         */
//        paint.setColor(Color.argb(Color.alpha(mOuterColor), Color.red(mOuterColor), Color.green(mOuterColor), Color.blue(mOuterColor)));
//        // 描边宽度
//        paint.setStrokeWidth(7);
//        // 描边种类
//        paint.setStyle(Paint.Style.STROKE);
//        // 外层text采用粗体
//        paint.setFakeBoldText(true);
//        canvas.drawText(text, start, end, x, y, paint);
//        StaticLayout staticLayout =StaticLayout.Builder.obtain(text, 0, text.length(), paint, 1200).build();
//        staticLayout.draw(canvas);
        /**
         * 描内层，恢复原先的画笔
         */
//        paint.setColor(Color.argb(Color.alpha(mInnerColor), Color.red(mInnerColor), Color.green(mInnerColor), Color.blue(mInnerColor)));
//        paint.setStyle(Paint.Style.FILL);
//        paint.setFakeBoldText(false);
//        canvas.drawText(text, start, end, x, y, paint);
        int[] startRowIndex=getRowIndex(start,singleRowNumber,row);
        int[] endRowIndex=getRowIndex(end,singleRowNumber,row);
        for (int i=0;i<row;i++){
          if (startRowIndex[0]==i){
              paint.setColor(Color.argb(Color.alpha(mOuterColor), Color.red(mOuterColor), Color.green(mOuterColor), Color.blue(mOuterColor)));
              // 描边宽度
              paint.setStrokeWidth(7);
              // 描边种类
              paint.setStyle(Paint.Style.STROKE);
              // 外层text采用粗体
              paint.setFakeBoldText(true);

              int endT;
              if (i==0){
                  endT= singleRowNumber;
              }else {
                  endT= singleRowNumber*i;
              }


              for (int k=i;k<row;k++){
                  if (startRowIndex[0]==k){
//                      canvas.drawText(text, start, endT, startRowIndex[1]*mTextSize, y*(startRowIndex[0]+1), paint);
                      canvas.drawText(text, start, endT, startRowIndex[1]*mTextSize, y*(startRowIndex[0]+1)/2, paint);
                  }else {
                      if (endRowIndex[0]>k){
                          canvas.drawText(text, k* singleRowNumber, k* singleRowNumber+singleRowNumber, startRowIndex[1]*mTextSize, y*(startRowIndex[0]+1), paint);
                      }else if (endRowIndex[0]==k){
                          canvas.drawText(text, k* singleRowNumber, k* singleRowNumber+singleRowNumber, startRowIndex[1]*mTextSize, y*(startRowIndex[0]+1), paint);
                      }else {
                          break;
                      }
                  }
              }
          }
        }


    }

    /**
     *
     * @param index  当前要找的index
     * @param singleRowNumber 一行字数
     * @param row  总行数
     * @return
     */
    private int[] getRowIndex(int index,float singleRowNumber,int row){
        int[] pos=new int[2];
        for (int i=0;i<row;i++){
             if (index<i*singleRowNumber){
                 //在该行内
                 int startRow=i-1;
                 if (startRow>0){
                     //所在行号  从0开始
                     pos[0]=startRow;
                     //所在行号 下标
                     pos[1]=(int) ((startRow+1)* singleRowNumber-index);
                 }else {
                     pos[0]=0;
                     pos[1]=index;
                 }
                 break;
             }else if (index==i*singleRowNumber){
                 //在该行内
                 if (i >0){
                     //所在行号  从0开始
                     pos[0]= i;
                     //所在行号 下标
                     pos[1]=(int)((i +1)* singleRowNumber-index);
                 }else {
                     pos[0]=0;
                     pos[1]=(int)(singleRowNumber-index);
                 }
                 break;
             }
        }
        return pos;
    }
}
