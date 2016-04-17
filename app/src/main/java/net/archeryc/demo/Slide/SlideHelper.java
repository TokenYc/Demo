package net.archeryc.demo.Slide;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by 24706 on 2016/4/15.
 */
public class SlideHelper {


    private static final int DEFAULT_START_X = -1000;
    private static final int MIN_REACT = 60;

    private View rootView;
    private int startX = DEFAULT_START_X;

    private  Context mContext;
    private Activity mActivity;


    public SlideHelper() {

    }

    public  void  init(Context context){
        mContext=context;
        mActivity=(Activity)context;

        rootView = ((Activity)mContext).findViewById(android.R.id.content).getRootView();
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

                            if ((int) event.getX() < SlideHelper.screenWidth(mContext) / 2) {
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
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "scroll", x, SlideHelper.screenWidth(mContext))
                .setDuration(300);
        objectAnimator.start();
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float position = (float) animation.getAnimatedValue();
                view.scrollTo(-(int) position, 0);
                if (position == SlideHelper.screenWidth(mContext)) {
                    mActivity.finish();
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

    /**
     * @param context
     * @return int
     * @description: 获取屏幕宽
     */
    public static int screenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
