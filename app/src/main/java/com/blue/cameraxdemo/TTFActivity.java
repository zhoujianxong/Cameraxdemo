package com.blue.cameraxdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.TextView;

public class TTFActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttf);
        TextView textView=findViewById(R.id.tv);
        for (int i=0;i<5;i++){
            SpannableString ss = new SpannableString(textView.getText());
            Drawable drawable = ContextCompat.getDrawable(this,R.drawable.cover11);
            drawable.setBounds(0,0,40,40);
            ImageSpan imageSpan=new ImageSpan(drawable);
            ss.setSpan(imageSpan,i,i+1,SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
            textView.setText(ss);
        }


    }
}
