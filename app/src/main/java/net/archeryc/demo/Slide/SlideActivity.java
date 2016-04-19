package net.archeryc.demo.slide;

import android.os.Bundle;

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
