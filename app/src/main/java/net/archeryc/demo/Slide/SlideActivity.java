package net.archeryc.demo.Slide;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import net.archeryc.demo.MainActivity;
import net.archeryc.demo.R;
import net.archeryc.demo.base.BaseActivity;

public class SlideActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        SlideHelper slideHelper=new SlideHelper();
        slideHelper.init(this);
    }

}
