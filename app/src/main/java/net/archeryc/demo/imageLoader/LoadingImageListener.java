package net.archeryc.demo.imageLoader;

import android.graphics.Bitmap;

/**
 * Created by 24706 on 2016/5/12.
 */
public interface LoadingImageListener {
    public void onLoadStart();

    public void onLoadFailure();

    public void onLoadSuccess(Bitmap bitmap);
}
