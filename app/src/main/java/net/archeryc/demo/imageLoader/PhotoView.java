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

    private Context mContext;
    private GestureDetector gestureDetector;
    private Bitmap mBitmap;
    private int screenWidth;
    private int screenHeigh;

    public PhotoView(Context context, String url) {
        super(context);
        this.mContext = context;
        this.setBackgroundColor(Color.BLACK);
        loadImage(url);
        gestureDetector = new GestureDetector(mContext, this);
        gestureDetector.setOnDoubleTapListener(this);


    }


    private void loadImage(String url) {
        Glide.with(mContext)
                .load(url)
                .asBitmap()
                .fitCenter()//centerCrop设置填充满imageview，可能有部分被裁剪掉，还有一种方式是fitCenter，将图片完整显示
                .into(simpleTarget);
    }

    SimpleTarget<Bitmap> simpleTarget = new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
            mBitmap=resource;
//            Matrix matrix = new Matrix();
//            matrix.setScale(1f, 1f);
//            Bitmap result = Bitmap.createBitmap(resource, 0, 0, resource.getWidth()/2, resource.getHeight()/2,matrix,true);
//            setImageBitmap(result);
            setImageBitmap(resource);
        }
    };


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
        ObjectAnimator objectAnimator = ObjectAnimator
                .ofFloat(this, "doubleTap", 1f, 0.5f)
                .setDuration(300);
        objectAnimator.start();
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value= (float) animation.getAnimatedValue();
                Matrix matrix=new Matrix(getMatrix());
                matrix.setScale(1.0f,1.0f);
                Bitmap bitmap = Bitmap.createBitmap(mBitmap, 0, 0, (int)(mBitmap.getWidth() * value), (int)(mBitmap.getHeight() * value), matrix, false);
                setImageBitmap(bitmap);
            }
        });

        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }


}
