package net.archeryc.demo.imageLoader;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import net.archeryc.demo.R;

public class LongImageActivity extends AppCompatActivity {

    private static final String LONG_IMAGE_URL = "http://pic2.ooopic.com/12/15/47/61bOOOPIC66_1024.jpg";

    private ImageView imageView;

    private SimpleTarget target = new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
            // do something with the bitmap
            // for demonstration purposes, let's just set it to an ImageView
            Matrix matrix = new Matrix();
            matrix.setScale(2.0f, 2.0f);
            Bitmap result = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            imageView.setImageBitmap(result);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_image);
        imageView = (ImageView) findViewById(R.id.longImage);
        ImageLoader.loadImage(this, LONG_IMAGE_URL, target);

    }
}
