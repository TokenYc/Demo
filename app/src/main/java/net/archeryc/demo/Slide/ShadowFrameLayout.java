package net.archeryc.demo.Slide;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by 24706 on 2016/4/18.
 */
public class ShadowFrameLayout extends FrameLayout {
    Context mContext;
    Paint paint;

    public ShadowFrameLayout(Context context) {
        super(context);
        mContext=context;
        init();
    }

    public ShadowFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        init();
    }

    private void init(){
        paint = new Paint();
        setWillNotDraw(false);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 设定颜色
        paint = new Paint();
        paint.setColor(0x00000000);

        // 设定阴影 (柔边, X轴位移, Y轴位移, 阴影颜色)
        paint.setShadowLayer(40, 20, 0, 0x31000000);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        // 空心矩形 & 其阴影
        canvas.drawRect( -30
                , 0
                , 0
                , SlideHelper.screenHeight(mContext)
                , paint
        );

    }
}
