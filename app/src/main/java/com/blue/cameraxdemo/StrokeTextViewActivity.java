package com.blue.cameraxdemo;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blue.cameraxdemo.spannable.RoundBackgroundColorSpan;
import com.blue.cameraxdemo.spannable.StrokeSpan;
import com.blue.cameraxdemo.spannable.StrokeTextView;
import com.blue.cameraxdemo.view.MyTextView;

public class StrokeTextViewActivity extends AppCompatActivity {
    private TextView textView;
    private LinearLayout linearLayout;
    private MyTextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stroke_text_view);
        textView = findViewById(R.id.textView);
        linearLayout = findViewById(R.id.linearLayout);
        myTextView=findViewById(R.id.MyTextView);
//        way();
//        way2();
        way3();
    }

    private void way() {
        int bgColor = Color.parseColor("#ff8041");
        int textColor = Color.parseColor("#008577");
        String s = textView.getText().toString();
        SpannableString spannableString = new SpannableString(s);
        RoundBackgroundColorSpan span = new RoundBackgroundColorSpan(bgColor, textColor, 15);
        spannableString.setSpan(span, 0, s.length() / 4, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
    }

    private void way2() {
        int color1 = Color.parseColor("#ff8041");
        int color2 = Color.parseColor("#008577");
        StrokeTextView strokeTextView = new StrokeTextView(this, color1, color2);
        strokeTextView.setText(R.string.test2);
        strokeTextView.setTextSize(40);
        linearLayout.addView(strokeTextView);
    }

    private void way3() {
        //尽量中心颜色突出
        int color1 = ContextCompat.getColor(this, R.color.b);
        int color2 = ContextCompat.getColor(this, R.color.a);

        String s = textView.getText().toString();
        SpannableString spannableString = new SpannableString(s);

        StrokeSpan strokeSpan = new StrokeSpan(color1, color2,textView.getTextSize());
//        StrokeSpan strokeSpan2 = new StrokeSpan(color2, color1);

        spannableString.setSpan(strokeSpan, 3, 21, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//        spannableString.setSpan(strokeSpan2, 20, 39, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


//        //设置字体大小
//        spannableString.setSpan(new AbsoluteSizeSpan(60), 20, 40, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        // 设置字体前景色
//        spannableString.setSpan(new ForegroundColorSpan(Color.MAGENTA), 20, 40, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        spannableString.setSpan(new ImageSpan(getDrawable(R.drawable.cover11)){
//            @Override
//            public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
//                Log.v("TAG","----- text = "+text+"  start="+start+"  end="+end);
//                return (int) paint.measureText(text,start,text.length());
//            }
//
//            @Override
//            public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint pt) {
//                Log.v("TAG","----- text = "+text+"  x="+x+"  y="+y);
//                super.draw(canvas, text, start, end, x, top, y, bottom, paint);
//                /**
//                 * 描外层
//                 */
//                TextPaint paint=new TextPaint();
//                paint.setTextSize(60);
//                paint.setColor(Color.argb(Color.alpha(color2), Color.red(color2), Color.green(color2), Color.blue(color2)));
//                // 描边宽度
//                paint.setStrokeWidth(2);
//                // 描边种类
//                paint.setStyle(Paint.Style.STROKE);
//                // 外层text采用粗体
//                paint.setFakeBoldText(true);
//                canvas.drawText(text, start, end, x, y, paint);
//
//                 //描内层，恢复原先的画笔
//                paint.setColor(Color.argb(Color.alpha(color1), Color.red(color1), Color.green(color1), Color.blue(color1)));
//                paint.setStyle(Paint.Style.FILL);
//                paint.setFakeBoldText(false);
//                canvas.drawText(text, start, end, x, y, paint);
//                StaticLayout staticLayout= StaticLayout.Builder.obtain(text, 0, text.length()-1, paint, 1200).build();
//                staticLayout.draw(canvas);
//            }
//        }, 0, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

//        ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.RED);
//        ForegroundColorSpan whiteSpan = new ForegroundColorSpan(Color.WHITE);
//        spannableString.setSpan(redSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        spannableString.setSpan(whiteSpan, 1, 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        textView.setText(spannableString);


        myTextView.setmMyColor(color1,color2);
        myTextView.setText(R.string.test3);

    }

}
