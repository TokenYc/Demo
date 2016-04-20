package net.archeryc.demo.slide;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import net.archeryc.demo.MyApplication;

import java.util.List;

/**
 * Created by 24706 on 2016/4/15.
 */
public class SlideHelper {


    private static final int DEFAULT_START_X = -1000;
    private static final int MIN_REACT = 60;

    private ViewGroup rootView;
    private ViewGroup secondRootView;
    private int startX = DEFAULT_START_X;

    private  Context mContext;
    private Activity mActivity;
    private Activity mSecondActivity;

    private float mScreenWidth;

    private ShadowFrameLayout slidePanel;
    private FrameLayout dimView;
    private OnPositionChangeListener onPositionChangeListener;

    public SlideHelper() {

    }

    public  void  init(Context context){
        mContext=context;
        mActivity=(Activity)context;
        mSecondActivity=getSecondActivity(mActivity);


        rootView = (ViewGroup) (mActivity).findViewById(android.R.id.content).getRootView();
        secondRootView=(ViewGroup) (mSecondActivity).findViewById(android.R.id.content).getRootView();

        dimView = new FrameLayout(mContext);
//        dimView.setBackgroundColor(Color.parseColor("#000000"));
        slidePanel = new ShadowFrameLayout(mContext,this);
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
                            secondRootView.scrollTo(200-(int) (event.getX()/(float)(screenWidth(mSecondActivity))*200),0);
//                            changeRootViewAlpha(dimView,event.getX());
                            if (onPositionChangeListener!=null)
                            onPositionChangeListener.onPositionChanged(event.getX());
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

    private Activity getSecondActivity(Activity mActivity) {
        List<Activity> activities= MyApplication.getInstance().getActivities();

        return activities.get(activities.indexOf(mActivity)-1);
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
                secondRootView.scrollTo(200-(int) (position/(float)(screenWidth(mSecondActivity))*200),0);

                if (position == SlideHelper.screenWidth(mContext)) {
                    dimView.setAlpha(0);
                    secondRootView.scrollTo(0,0);
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
                secondRootView.scrollTo(200-(int) (position/(float)(screenWidth(mSecondActivity))*200),0);

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


    public void setOnPositionChangeListener(OnPositionChangeListener listener) {
        this.onPositionChangeListener=listener;
    }

    public interface OnPositionChangeListener{
         void onPositionChanged(float x);
    }


}
