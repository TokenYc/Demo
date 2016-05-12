package net.archeryc.demo.imageLoader;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import net.archeryc.demo.MainActivity;
import net.archeryc.demo.R;

import java.util.ArrayList;
import java.util.List;

public class BigPhotoActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MyViewPagerAdapter adapter;
    private String[] urls = {
            "http://ww1.sinaimg.cn/large/7a8aed7bjw1f3k9dp8r9qj20dw0jljtd.jpg",
            "http://ww1.sinaimg.cn/large/7a8aed7bgw1f3j8jt6qn8j20vr15owvk.jpg",
            "http://ww1.sinaimg.cn/large/7a8aed7bgw1f3damign7mj211c0l0dj2.jpg",
            "http://ww2.sinaimg.cn/large/7a8aed7bjw1f3c7zc3y3rj20rt15odmp.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_photo);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new MyViewPagerAdapter(urls);
        viewPager.setAdapter(adapter);
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window=getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
    }


    class MyViewPagerAdapter extends PagerAdapter {

        private List<View> photoViews;
        private String[] urls;

        public MyViewPagerAdapter(String[] urls) {
            this.urls = urls;
            photoViews = new ArrayList<>();
        }

        @Override
        public int getCount() {
            return urls.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(photoViews.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(BigPhotoActivity.this,urls[position]);
            photoViews.add(photoView);
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            return photoView;
        }


    }
}
