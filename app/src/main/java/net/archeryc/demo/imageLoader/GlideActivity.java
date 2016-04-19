package net.archeryc.demo.imageLoader;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import net.archeryc.demo.R;
import net.archeryc.demo.base.BaseActivity;

public class GlideActivity extends BaseActivity {

    private static final String normalUrl = "http://ww1.sinaimg.cn/large/7a8aed7bjw1f2zwrqkmwoj20f00lg0v7.jpg";
    private static final String gifUrl = "http://ww2.sinaimg.cn/large/85cccab3tw1esjq9r0pcpg20d3086qtr.jpg";

    private ImageView imageView;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        imageView = (ImageView) findViewById(R.id.imageView);
    }


    public void showNormalImage(View view) {
        ImageLoader.loadImage(this,normalUrl,imageView);
    }

    public void showResourceImage(View view) {
        ImageLoader.loadImage(this,R.mipmap.ic_launcher,imageView);
    }

    public void showGif(View view) {
        ImageLoader.loadGif(this,gifUrl,imageView);
    }

    public void showLongImage(View view) {
        startActivity(new Intent(this,LongImageActivity.class));
    }
}
