package net.archeryc.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

public class ViewStubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stub);
    }

    /**
     * 可以应用到一些一开始要隐藏，后面再显示的视图中，减少开销。viewstub很轻量级
     * @param view
     */
    public void showViewStub(View view){
//        ((ViewStub) findViewById(R.id.viewstub)).setVisibility(View.VISIBLE);//第一种直接显示layout
        ViewStub importView = (ViewStub)findViewById(R.id.viewstub);//第二种通过inflate，这样可以引入那个layout
        importView.inflate();
    }
}
