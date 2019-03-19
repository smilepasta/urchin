package com.smilepasta.urchin.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.smilepasta.urchin.R;

import java.io.File;

/**
 * Author: huangxiaoming
 * Date: 2018/11/20
 * Desc:
 * Version: 1.0
 */
public class GlideUtil {
    public static void loadImage(String url, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.error)
                .error(R.mipmap.error)
                .crossFade()
                .into(imageView);
    }

    public static void loadImage(File file, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(file)
                .placeholder(R.mipmap.error)
                .error(R.mipmap.error)
                .crossFade()
                .into(imageView);
    }

    public static void loadImage(int resourceId, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(resourceId)
                .placeholder(R.mipmap.error)
                .error(R.mipmap.error)
                .crossFade()
                .into(imageView);
    }

    public static void loadImage(Uri uri, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(uri)
                .placeholder(R.mipmap.error)
                .error(R.mipmap.error)
                .crossFade()
                .into(imageView);
    }

    public static void loadImage(Bitmap bitmap, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(bitmap)
                .placeholder(R.mipmap.error)
                .error(R.mipmap.error)
                .crossFade()
                .into(imageView);
    }

}
