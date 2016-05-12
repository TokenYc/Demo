package net.archeryc.demo.imageLoader;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import net.archeryc.demo.R;
import net.archeryc.demo.slide.SlideHelper;

public class LongImageActivity extends AppCompatActivity {
    //http://pic2.ooopic.com/12/15/47/61bOOOPIC66_1024.jpg
    //http://ww3.sinaimg.cn/mw690/8345c393jw1f32xv7zd4gj20go24yaqv.jpg
    //http://ww3.sinaimg.cn/mw690/7279d218jw1f2xfvdzrm3j20c83rj4h0.jpg
    private static final String LONG_IMAGE_URL = "http://ww1.sinaimg.cn/large/7a8aed7bjw1f2zwrqkmwoj20f00lg0v7.jpg";

    private ImageView imageView;
    private ImageView imv_progress;
    private LongClickDialog longClickDialog;
    private AnimationDrawable animationDrawable;
    private GestureDetector detector;
    private float multiple;
    private Bitmap mBitmap;

    private LoadingImageListener imageListener=new LoadingImageListener() {
        @Override
        public void onLoadStart() {

        }

        @Override
        public void onLoadFailure() {

        }

        @Override
        public void onLoadSuccess(Bitmap bitmap) {
            mBitmap=bitmap;
            int width=mBitmap.getWidth();
            multiple=(float)(SlideHelper.screenWidth(LongImageActivity.this))/(width);
            Log.d("multiple", "multiple:" + multiple);
            Matrix matrix = new Matrix();
            matrix.setScale((float) (multiple), (float) (multiple));
            Bitmap result = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(),matrix,true);
            imageView.setImageBitmap(result);
            animationDrawable.stop();
            imv_progress.setVisibility(View.GONE);
            imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    longClickDialog.setBitmap(mBitmap);
                    longClickDialog.show();
                    return false;
                }
            });
            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    detector.onTouchEvent(event);
                    return false;
                }
            });
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_image);
        imv_progress = (ImageView) findViewById(R.id.imv_progress);
        animationDrawable= (AnimationDrawable) imv_progress.getBackground();
        imageView = (ImageView) findViewById(R.id.longImage);
        GlideImageLoader.getInstance().loadImage(this, LONG_IMAGE_URL, imageListener);
        animationDrawable.start();

        longClickDialog = new LongClickDialog(this);
        detector=new GestureDetector(this,new GestureDetector.OnGestureListener() {
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
        });

        detector.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
//                Matrix matrix = new Matrix();
//                multiple*=1.2;
//                matrix.setScale(multiple,multiple);
//                Bitmap result = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(),matrix,true);
//                imageView.setImageBitmap(result);
                return false;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return false;
            }
        });

    }
}
