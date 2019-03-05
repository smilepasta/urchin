package com.smilepasta.urchin.widget.textview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.text.Html;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * Author: huangxiaoming
 * Date: 2019/2/20
 * Desc: 使Html标签中的图片显示在TextView中
 * Version: 1.0
 */
public class ImageGetter implements Html.ImageGetter {

    private Context context;
    private TextView textView;

    public ImageGetter(Context context, TextView textView) {
        this.context = context;
        this.textView = textView;
    }

    @Override
    public Drawable getDrawable(String source) {
        final LevelListDrawable drawable = new LevelListDrawable();
        Glide.with(context).load(source).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                if (resource != null) {
                    BitmapDrawable bitmapDrawable = new BitmapDrawable(resource);
                    drawable.addLevel(1, 1, bitmapDrawable);
                    drawable.setBounds(0, 0, resource.getWidth(), resource.getHeight());
                    drawable.setLevel(1);
                    CharSequence text = textView.getText();
                    textView.setText(text);
                    textView.refreshDrawableState();
                }
            }
        });
        return drawable;
    }
}
