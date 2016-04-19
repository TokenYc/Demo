package net.archeryc.demo.imageLoader;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import net.archeryc.demo.R;

public class LongImageActivity extends AppCompatActivity {

    private static final String LONG_IMAGE_URL="http://b.hiphotos.baidu.com/zhidao/pic/item/562c11dfa9ec8a1322b385c6f503918fa0ecc06f.jpg";

    private ImageView imageView;

    private SimpleTarget target = new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
            // do something with the bitmap
            // for demonstration purposes, let's just set it to an ImageView
            imageView.setImageBitmap( bitmap );
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_image);
        imageView = (ImageView) findViewById(R.id.longImage);
        ImageLoader.loadImage(this, LONG_IMAGE_URL,target);

    }
}
