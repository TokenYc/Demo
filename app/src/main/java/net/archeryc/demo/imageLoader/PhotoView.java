package net.archeryc.demo.imageLoader;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * Created by 24706 on 2016/5/5.
 */
public class PhotoView extends ImageView implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private static final float MAX_SCALE = 1.75f;
    private static final float NORMAL_SCALE = 1.0f;
    private Context mContext;
    private GestureDetector gestureDetector;
    private Bitmap mBitmap;
    private Matrix mMatrix;
    private boolean isHorizontal;
    private float currentScaleType = NORMAL_SCALE;

    public PhotoView(Context context, String url) {
        super(context);
        this.mContext = context;
        this.setBackgroundColor(Color.BLACK);
        this.setScaleType(ScaleType.MATRIX);
        loadImage(url);
        gestureDetector = new GestureDetector(mContext, this);
        gestureDetector.setOnDoubleTapListener(this);
        mMatrix = getMatrix();
    }


    private void loadImage(String url) {
        Glide.with(mContext)
                .load(url)
                .asBitmap()
                .override(getScreenWidth(), getScreenHeight())
                .fitCenter()//centerCrop设置填充满imageview，可能有部分被裁剪掉，还有一种方式是fitCenter，将图片完整显示
                .into(simpleTarget);
    }

    SimpleTarget<Bitmap> simpleTarget = new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
            mBitmap = resource;
//            Matrix matrix = new Matrix();
//            matrix.setScale(1f, 1f);
//            Bitmap result = Bitmap.createBitmap(resource, 0, 0, resource.getWidth()/2, resource.getHeight()/2,matrix,true);
//            setImageBitmap(result);
            setImageBitmap(resource);
            setCenter();
//            float screenWidth=(float)getResources().getDisplayMetrics().widthPixels;
//            float screenHeight=(float)getResources().getDisplayMetrics().heightPixels;
//
//            float multiple=screenWidth/getWidth();
//            setScale(multiple,multiple);
        }
    };

    public void setScale(float sx, float sy, float px, float py) {
        if (mMatrix == null) {
            mMatrix = getMatrix();
        }
        mMatrix.postScale(sx, sy, px, py);
        setImageMatrix(mMatrix);
    }

    public void setScaleCenter(float sx, float sy) {
        setScale(sx, sy, (float) getScreenWidth() * 0.5f, (float) getScreenHeight() * 0.5f);
    }

    public void setCenter() {
        if (mMatrix == null) {
            mMatrix = getMatrix();
        }
        if (mBitmap.getWidth() < getScreenWidth()) {
            mMatrix.postTranslate((float) getScreenWidth() * 0.5f - (float) mBitmap.getWidth() * 0.5f, 0);
            isHorizontal=false;
        } else {
            mMatrix.postTranslate(0, (float) getScreenHeight() * 0.5f - (float) mBitmap.getHeight() * 0.5f);
            isHorizontal=true;
        }
        setImageMatrix(mMatrix);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }


    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.d("event", "onDouleTap");
        float x=e.getX();
        float y=e.getY();
        if (currentScaleType == NORMAL_SCALE) {
            startScaleAnim(NORMAL_SCALE, MAX_SCALE, e.getX(), (float)getScreenHeight()*0.5f);
            currentScaleType = MAX_SCALE;
        } else {
            startScaleAnim(MAX_SCALE, NORMAL_SCALE, e.getX(), (float)getScreenHeight()*0.5f);
            currentScaleType = NORMAL_SCALE;
        }


        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    private int getScreenWidth() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    private int getScreenHeight() {
        return getResources().getDisplayMetrics().heightPixels;
    }

    public void startScaleAnim(float start, float end, final float x, final float y) {
        ObjectAnimator objectAnimator = ObjectAnimator
                .ofFloat(this, "doubleTap", start, end)
                .setDuration(300);
        objectAnimator.start();
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                setScale(value, value, x, y);
            }
        });
        //高为屏幕高度

    }
}
