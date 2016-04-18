package net.archeryc.demo.Slide;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by 24706 on 2016/4/15.
 */
public class SlideHelper {


    private static final int DEFAULT_START_X = -1000;
    private static final int MIN_REACT = 60;

    private ViewGroup rootView;
    private int startX = DEFAULT_START_X;

    private  Context mContext;
    private Activity mActivity;

    private float mScreenWidth;

    private ShadowFrameLayout slidePanel;
    private FrameLayout dimView;

    public SlideHelper() {

    }

    public  void  init(Context context){
        mContext=context;
        mActivity=(Activity)context;

        rootView = (ViewGroup) (mActivity).findViewById(android.R.id.content).getRootView();
        dimView = new FrameLayout(mContext);
//        dimView.setBackgroundColor(Color.parseColor("#000000"));
        slidePanel = new ShadowFrameLayout(mContext);
        for (int i=0;i<rootView.getChildCount();i++){
            View view = rootView.getChildAt(i);
            rootView.removeViewAt(i);
            slidePanel.addView(view);
        }
        rootView.addView(dimView);
        rootView.addView(slidePanel);

        mScreenWidth =(float)screenWidth(mContext);
        slidePanel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if ((int) event.getX() < MIN_REACT)
                            startX = (int) event.getX();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (startX > DEFAULT_START_X) {
                            slidePanel.scrollTo(-(int) event.getX(), 0);
//                            changeRootViewAlpha(dimView,event.getX());
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (startX > DEFAULT_START_X) {

                            startX = DEFAULT_START_X;

                            if ((int) event.getX() < SlideHelper.screenWidth(mContext) / 2) {//没有退出activity
                                backToBegin(slidePanel, event.getX());
                                rootView.setAlpha(1f);
                            } else {//退出activity
                                goToEnd(slidePanel, event.getX());
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
                    dimView.setAlpha(0);
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

    private void changeRootViewAlpha( View rootView,float currentX){

        float value= (float) ((1-currentX/ mScreenWidth)/1.5);


        rootView.setAlpha(value);

    }
    
    /**
     * @param context
     * @return int
     * @description: 获取屏幕宽
     */
    public static int screenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度
     * @param context
     * @return
     */
    public static int screenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}