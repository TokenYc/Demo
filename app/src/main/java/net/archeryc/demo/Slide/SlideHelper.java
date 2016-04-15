package net.archeryc.demo.Slide;

import android.content.Context;
import android.widget.Scroller;

/**
 * Created by 24706 on 2016/4/15.
 */
public class SlideHelper {

    /**
     * @param context
     * @return int
     * @description: 获取屏幕宽
     */
    public static int screenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }


    static class MyScroller extends Scroller{

        public MyScroller(Context context) {
            super(context);
        }

    }
}
