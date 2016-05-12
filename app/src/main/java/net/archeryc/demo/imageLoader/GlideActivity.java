package net.archeryc.demo.imageLoader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import net.archeryc.demo.R;
import net.archeryc.demo.base.BaseActivity;

public class GlideActivity extends BaseActivity {

    //http://ww1.sinaimg.cn/large/7a8aed7bjw1f2zwrqkmwoj20f00lg0v7.jpg
    //http://ww3.sinaimg.cn/mw690/8345c393jw1f32xv7zd4gj20go24yaqv.jpg
    private static final String normalUrl = "http://ww1.sinaimg.cn/large/7a8aed7bjw1f2zwrqkmwoj20f00lg0v7.jpg";
    private static final String gifUrl = "http://ww2.sinaimg.cn/large/85cccab3tw1esjq9r0pcpg20d3086qtr.jpg";

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        imageView = (ImageView) findViewById(R.id.imageView);
    }


    public void showNormalImage(View view) {
        GlideImageLoader.getInstance().loadCircleImage(this,normalUrl,imageView);
    }

    public void showResourceImage(View view) {
        GlideImageLoader.getInstance().loadImage(this,R.mipmap.ic_launcher,imageView);
    }

    public void showGif(View view) {
        GlideImageLoader.getInstance().loadGif(this,gifUrl,imageView);
    }

    public void showLongImage(View view) {
        startActivity(new Intent(this,LongImageActivity.class));
    }

    public void showBigImage(View view){
        startActivity(new Intent(this,BigPhotoActivity.class));
    }
}
