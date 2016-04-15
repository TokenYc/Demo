package net.archeryc.demo.Slide;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import net.archeryc.demo.MainActivity;
import net.archeryc.demo.R;

public class SlideActivity extends AppCompatActivity {

    private static final int DEFAULT_START_X = -1000;
    private static final int MIN_REACT = 60;

    private View rootView;
    private int startX = DEFAULT_START_X;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);


        rootView = findViewById(android.R.id.content).getRootView();
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if ((int) event.getX() < MIN_REACT)
                            startX = (int) event.getX();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (startX > DEFAULT_START_X)
                            rootView.scrollTo(-(int) event.getX(), 0);
                        break;
                    case MotionEvent.ACTION_UP:
                        if (startX > DEFAULT_START_X) {

                            startX = DEFAULT_START_X;

                            if ((int) event.getX() < SlideHelper.screenWidth(SlideActivity.this) / 2) {
                                backToBegin(rootView, event.getX());
                            } else {
                                goToEnd(rootView, event.getX());
                            }
                        }
                        break;
                }
                return true;
            }
        });
    }

    /**
     * 直接结束页面
     *
     * @param x
     */
    private void goToEnd(final View view, float x) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "scroll", x, SlideHelper.screenWidth(SlideActivity.this))
                .setDuration(300);
        objectAnimator.start();
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float position = (float) animation.getAnimatedValue();
                view.scrollTo(-(int) position, 0);
                if (position == SlideHelper.screenWidth(SlideActivity.this)) {
                    finish();
                }
            }
        });
    }

    /**
     * 回到初始状态
     *
     * @param x
     */
    private void backToBegin(final View view, float x) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "scroll", x, 0)
                .setDuration(300);
        objectAnimator.start();
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float position = (float) animation.getAnimatedValue();
                view.scrollTo(-(int) position, 0);
            }
        });
    }


}
