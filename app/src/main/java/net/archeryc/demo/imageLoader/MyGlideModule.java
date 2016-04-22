package net.archeryc.demo.imageLoader;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by 24706 on 2016/4/21.
 */
//自定义全局Glide
public class MyGlideModule implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
//            MemorySizeCalculator calculator = new MemorySizeCalculator(context);
//            int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
//            int defaultBitmapPoolSize = calculator.getBitmapPoolSize();//获取默认的内存缓存
//
//            int customMemoryCacheSize = (int) (0.5 *(float) defaultMemoryCacheSize);//自定义内存缓存大小
//            int customBitmapPoolSize = (int) (0.5 * (float)defaultBitmapPoolSize);//自定义图片缓存池大小
//
//            builder.setMemoryCache(new LruResourceCache(customMemoryCacheSize));
//            builder.setBitmapPool( new LruBitmapPool( customBitmapPoolSize ));

        // set size & external vs. internal
//            int cacheSize100MegaBytes = 104857600;//设置磁盘缓存大小
//
//            builder.setDiskCache(
//                    new InternalCacheDiskCacheFactory(context, cacheSize100MegaBytes)
//            );
//
//            //builder.setDiskCache(
//            //new ExternalCacheDiskCacheFactory(context, cacheSize100MegaBytes));

        // or any other path
//            String downloadDirectoryPath = Environment.getDownloadCacheDirectory().getPath();//自定义外部缓存
//
//            builder.setDiskCache(
//                    new DiskLruCacheFactory( downloadDirectoryPath, cacheSize100MegaBytes )
//            );
//
//// In case you want to specify a cache sub folder (i.e. "glidecache"):
////builder.setDiskCache(
////    new DiskLruCacheFactory( downloadDirectoryPath, "glidecache", cacheSize100MegaBytes )
////);
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
