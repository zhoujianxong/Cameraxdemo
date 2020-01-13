package com.blue.cameraxdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class Main2Activity extends AppCompatActivity {
    private VelocityTracker velocityTracker;
    private SpringAnimation springAnimX,springAnimY;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ImageView imageView = findViewById(R.id.id_spring);
        ImageView imageView2 = findViewById(R.id.id_spring2);
        FloatingActionButton fab = findViewById(R.id.fab);

        SpringForce force = new SpringForce(0)
                .setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY)
                .setStiffness(SpringForce.STIFFNESS_VERY_LOW);
        springAnimX = new SpringAnimation(imageView, DynamicAnimation.TRANSLATION_X).setSpring(force);
        springAnimY=new SpringAnimation(imageView, DynamicAnimation.TRANSLATION_Y).setSpring(force);

        SpringAnimation springAnim2X = new SpringAnimation(imageView2, DynamicAnimation.TRANSLATION_X, 0);
        SpringAnimation springAnim2Y = new SpringAnimation(imageView2, DynamicAnimation.TRANSLATION_Y, 0);

        fab.setOnClickListener(view -> {
            Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            SpringAnimation springAnim = new SpringAnimation(imageView, DynamicAnimation.TRANSLATION_Y,0);
            //设置弹簧的刚度。弹簧刚度越大，当弹簧不在最终位置时，它施加到*附着物体上的力就越大。默认刚度为  相当于弹簧系数 k
            springAnim.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);
            //弹簧阻尼比描述了扰动后系统的振荡如何衰减。
            // * <p> *当阻尼比> 1（过阻尼）时，物体将快速返回到静止位置*而不会超调。如果阻尼比等于1（即临界阻尼），
            // 则物体将在最短时间内*恢复平衡。当阻尼比小于1 *（即阻尼不足）时，质量块倾向于过冲，
            // 然后返回并再次过冲。如果没有*任何阻尼（即阻尼比= 0），则质量块将永远振动
            springAnim.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY);
            springAnim.setStartVelocity(5000);
            springAnim.start();
        });


        springAnimX.addUpdateListener((animation, value, velocity) -> springAnim2X.animateToFinalPosition(value));
        springAnimY.addUpdateListener(new DynamicAnimation.OnAnimationUpdateListener() {
            @Override
            public void onAnimationUpdate(DynamicAnimation animation, float value, float velocity) {
                springAnim2Y.animateToFinalPosition(value);
            }
        });


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        acquireVelocityTracker(event);
        switch (action) {
            case MotionEvent.ACTION_MOVE:
                // 求伪瞬时速度
                velocityTracker.computeCurrentVelocity(1000);
                springAnimX.setStartVelocity(velocityTracker.getXVelocity());
                springAnimY.setStartVelocity(velocityTracker.getYVelocity());
                springAnimX.start();
                springAnimY.start();
                break;
            default:
                break;

        }
        return super.onTouchEvent(event);
    }

    private void acquireVelocityTracker(final MotionEvent event) {

        if (null == velocityTracker) {
            velocityTracker = VelocityTracker.obtain();
        }
        velocityTracker.addMovement(event);

    }
}
