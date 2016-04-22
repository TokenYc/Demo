package net.archeryc.demo.imageLoader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by 24706 on 2016/4/20.
 */
public class LoadingImageLayout extends RelativeLayout {

    private Paint paint;

    public LoadingImageLayout(Context context) {
        super(context);
        init();
    }



    public LoadingImageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // C4C4C4
        paint.setColor(Color.parseColor("#7A7A7A"));
        canvas.drawCircle(getWidth()/2-80,getHeight()/2,30,paint);
        paint.setColor(Color.parseColor("#7A7A7A"));
        canvas.drawCircle(getWidth()/2,getHeight()/2,30,paint);
        paint.setColor(Color.parseColor("#7A7A7A"));
        canvas.drawCircle(getWidth()/2+80,getHeight()/2,30,paint);
    }

}
