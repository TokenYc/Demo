package net.archeryc.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import net.archeryc.demo.Slide.SlideActivity;
import net.archeryc.demo.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void go2ThreadActivity(View view){
        startActivity(new Intent(this,ThreadActivity.class));
    }

    public void go2EditTextActivity(View view){
        startActivity(new Intent(this,EditTextActivity.class));
    }

    public void go2ViewStubActivity(View view) {
        startActivity(new Intent(this,ViewStubActivity.class));
    }

    public void go2SlideActivity(View view) {
        startActivity(new Intent(this,SlideActivity.class));
    }

    public void go2CanvasActivity(View view) {
        startActivity(new Intent(this,CanvasActivity.class));
    }

    public void go2GlideActivity(View view) {
        startActivity(new Intent(this, GlideActivity.class));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
