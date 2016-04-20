package net.archeryc.demo.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import net.archeryc.demo.R;
import net.archeryc.demo.slide.SlideHelper;


/**
 * Created by .cc on 2016/3/2.
 */
public abstract class BaseDialog extends Dialog {
    Context mContext;
    public BaseDialog(Context context) {
        super(context, R.style.DialogTheme);
        this.mContext=context;
        init();
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext=context;
        init();
    }

    public void init() {
        View convertView = LayoutInflater.from(getContext()).inflate(getConvetViewId(), null, false);
        setContentView(convertView);
        initData();
        initView();
        initListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setLayout(SlideHelper.screenWidth(getContext()), SlideHelper.screenHeight(mContext));
        getWindow().setBackgroundDrawable(new
                ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.dimAmount = 0.65f;
        getWindow().setAttributes(lp);
    }

    protected abstract int getConvetViewId();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void setDialogAttr(Dialog dialog);
}
