package com.blue.cameraxdemo;

import androidx.annotation.ColorInt;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blue.cameraxdemo.spannable.RoundBackgroundColorSpan;
import com.blue.cameraxdemo.spannable.StrokeSpan;
import com.blue.cameraxdemo.spannable.StrokeTextView;

public class StrokeTextViewActivity extends AppCompatActivity {
    private TextView textView;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stroke_text_view);
        textView = findViewById(R.id.textView);
        linearLayout = findViewById(R.id.linearLayout);
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

        StrokeSpan strokeSpan = new StrokeSpan(color1, color2);
        spannableString.setSpan(strokeSpan, 20, 30, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        textView.setText(spannableString);


    }

}
