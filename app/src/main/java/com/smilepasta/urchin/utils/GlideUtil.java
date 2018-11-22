package com.smilepasta.urchin.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
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
                .error(R.mipmap.error)
                .crossFade()
                .into(imageView);
    }

    public static void loadImage(File file, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(file)
                .error(R.mipmap.error)
                .crossFade()
                .into(imageView);
    }

    public static void loadImage(int resourceId, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(resourceId)
                .error(R.mipmap.error)
                .crossFade()
                .into(imageView);
    }

    public static void loadImage(Uri uri, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(uri)
                .error(R.mipmap.error)
                .crossFade()
                .into(imageView);
    }

    public static void loadImage(Bitmap bitmap, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(bitmap)
                .error(R.mipmap.error)
                .crossFade()
                .into(imageView);
    }
}
