package net.archeryc.demo.imageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;

import net.archeryc.demo.R;

import java.io.File;

/**
 * Created by 24706 on 2016/4/19.
 */
public class ImageLoader {

    private static final int ERROR_IMAGE = R.drawable.load_failure;


    /**
     * 加载普通网络图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .placeholder(new ColorfulDrawable())
                .error(ERROR_IMAGE)
                .centerCrop()
                //设置填充满imageview，可能有部分被裁剪掉，还有一种方式是fitCenter，将图片完整显示
                .into(imageView);
    }


    public static void loadImage(Context context, String url, SimpleTarget target) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .placeholder(new ColorfulDrawable())
                .error(ERROR_IMAGE)
                .centerCrop()//设置填充满imageview，可能有部分被裁剪掉，还有一种方式是fitCenter，将图片完整显示
                .into(target);
    }

    /**
     * 从资源文件中加载图片
     *
     * @param context
     * @param sourceId
     * @param imageView
     */
    public static void loadImage(Context context, int sourceId, ImageView imageView) {
        Glide.with(context)
                .load(sourceId)
                .placeholder(new ColorfulDrawable())
                .error(ERROR_IMAGE)
                .centerCrop()
                .into(imageView);
    }

    /**
     * 从文件中加载图片
     *
     * @param context
     * @param file
     * @param imageView
     */
    public static void loadImage(Context context, File file, ImageView imageView) {
        Glide.with(context)
                .load(file)
                .placeholder(new ColorfulDrawable())
                .error(ERROR_IMAGE)
                .centerCrop()
                .into(imageView);

    }

    /**
     * 从Uri中加载图片
     *
     * @param context
     * @param uri
     * @param imageView
     */
    public static void loadImage(Context context, Uri uri, ImageView imageView) {
        Glide.with(context)
                .load(uri)
                .placeholder(new ColorfulDrawable())
                .error(ERROR_IMAGE)
                .centerCrop()
                .into(imageView);
    }




    /**
     * 从网络中加载Gif
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadGif(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .asGif()
                .placeholder(new ColorfulDrawable())
                .error(ERROR_IMAGE)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }


    /**
     * 从资源文件中加载Gif
     *
     * @param context
     * @param sourceId
     * @param imageView
     */
    public static void loadGif(Context context, int sourceId, ImageView imageView) {
        Glide.with(context)
                .load(sourceId)
                .asGif()
                .placeholder(new ColorfulDrawable())
                .error(ERROR_IMAGE)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

    /**
     * 从文件中加载Gif
     *
     * @param context
     * @param file
     * @param imageView
     */
    public static void loadGif(Context context, File file, ImageView imageView) {
        Glide.with(context)
                .load(file)
                .asGif()
                .placeholder(new ColorfulDrawable())
                .error(ERROR_IMAGE)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into(imageView);

    }

    /**
     * 从Uri中加载Gif
     *
     * @param context
     * @param uri
     * @param imageView
     */
    public static void loadGif(Context context, Uri uri, ImageView imageView) {
        Glide.with(context)
                .load(uri)
                .asGif()
                .placeholder(new ColorfulDrawable())
                .error(ERROR_IMAGE)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

}
